/*
package com.example.nacos_server.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.stardon.stardon_main.comm.Response;
import com.stardon.stardon_main.comm.ResultCodeEnum;
import com.stardon.stardon_main.pojo.*;
import com.stardon.stardon_main.service.ISysLogSecurityService;
import com.stardon.stardon_main.service.ISysLogService;
import com.stardon.stardon_main.service.ISysMenuService;
import com.stardon.stardon_main.service.ISysUserService;
import com.stardon.stardon_main.utils.DateUtils;
import com.stardon.stardon_main.utils.IPUtils;
import com.stardon.stardon_main.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Component
public class ZuulLogFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ISysLogService sysLogService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysMenuService sysMenuService;
    @Autowired
    private ISysLogSecurityService sysLogSecurityService;

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        String path = "";


        String ContentType = "";
        List<Pair<String, String>> headerList = ctx.getOriginResponseHeaders();
        for (Pair<String, String> pair : headerList) {
            if (pair.first().equals("Content-Type")) {
                ContentType = pair.second();
                break;
            }
        }
        if (ContentType == "" || !ContentType.equalsIgnoreCase("application/json;charset=UTF-8")) {
            logger.debug("Content-Type：" + ContentType);
            return null;
        }
        try {
            URL url = new URL(request.getRequestURL().toString());
            path = url.getPath();
        } catch (MalformedURLException e) {
            logger.error("URL解析异常：" + e.getMessage());
        }

        //请求url中包含stardon-token的是下载，不记录日志。
        if (path.indexOf("stardon-token") > -1) {
            return null;
        }

        SysLog sysLog = new SysLog();
        Subject subject = SecurityUtils.getSubject();
        String loginName = (String) subject.getPrincipal();
        SysUser user = null;
        if (loginName != null) {
            user = sysUserService.getUserByUname(loginName);
            sysLog.setUserid(user.getUserid());
            sysLog.setOrgid(user.getOrgid());
            sysLog.setUname(user.getUname().toString());
        }
        Map<String, String[]> parameterMap = request.getParameterMap();  //请求参数
        if (parameterMap != null) {
            sysLog.setOpContent(JSONObject.toJSONString(parameterMap));
        }
        sysLog.setOpIp(IPUtils.getIpAddr(request));
        sysLog.setOpTime(DateUtils.getCurrentTime());
        sysLog.setOpModule(path);
        sysLog.setOpType("-");
        String[] modules = path.split("/");
        SysMenu sysMenu = null;
        if (modules != null && modules.length == 4) {
            sysMenu = sysMenuService.selectOpType(modules[2], modules[3]);
            if (sysMenu != null) {
                String opType = sysMenu.getOpType();
                if (opType == null || opType == "") {
                    opType = "-";
                }
                sysLog.setOpType(opType);
                sysLog.setCoreFunction(sysMenu.getCoreFunction());
                sysLog.setRegularBusiness(sysMenu.getRegularBusiness());
                //安全审计日志
                if ("1".equals(sysMenu.getCoreFunction())) {
                    SysLogSecurity sysLogSecurity = new SysLogSecurity();
                    sysLogSecurity.setContent("访问核心业务[" + path + "]");
                    sysLogSecurity.setOpTime(DateUtils.getCurrentTime());
                    sysLogSecurity.setIp(IPUtils.getIpAddr(request));
                    sysLogSecurity.setOpType(opType);
                    sysLogSecurity.setType("39");
                    sysLogSecurity.setUname(user.getUname().toString());
                    sysLogSecurityService.insertSelective(sysLogSecurity);
                }
                if ("1".equals(sysMenu.getRegularBusiness())) {
                    SysLogSecurity sysLogSecurity = new SysLogSecurity();
                    sysLogSecurity.setContent("访问非常规业务[" + path + "]");
                    sysLogSecurity.setOpTime(DateUtils.getCurrentTime());
                    sysLogSecurity.setIp(IPUtils.getIpAddr(request));
                    sysLogSecurity.setOpType(opType);
                    sysLogSecurity.setType("40");
                    sysLogSecurity.setUname(user.getUname().toString());
                    sysLogSecurityService.insertSelective(sysLogSecurity);
                }
            }
        }
        //记录失败日志
        Response errorRes = (Response) ctx.get("zuul-error-info");
        if (errorRes != null) {
            sysLog.setOpResult(errorRes.getCode().toString());
            sysLog.setOpResultInfo(errorRes.getMsg());
            sysLog.setCheckDigit(MD5Utils.encryption(sysLog.toStringForCheck()));
            sysLogService.insertSelective(sysLog);
            return null;
        }

        if (response.getStatus() == 404) {
            ctx.setResponseBody(new Response(ResultCodeEnum.ServiceAddressNotFound).toJsonString());
            ctx.setResponseStatusCode(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println("404错误,请求地址:"+request.getRequestURL());
            return null;
        }
        else if (response.getStatus() == 400) {
            ctx.setResponseBody(new Response(ResultCodeEnum.ServiceErrQeustOrNotFound).toJsonString());
            ctx.setResponseStatusCode(HttpServletResponse.SC_NOT_FOUND);
            System.out.println("400错误,请求地址:"+request.getRequestURL());
            return null;
        }
        else if (response.getStatus() == 500){
            ctx.setResponseBody(new Response(ResultCodeEnum.ServiceException).toJsonString());
            ctx.setResponseStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.out.println("500错误,请求地址:"+request.getRequestURL());
            return null;
        }
        //记录成功日志
        String outBody = null;
        InputStream stream = ctx.getResponseDataStream();
//        List<Pair<String, String>> headerList = RequestContext.getCurrentContext().getOriginResponseHeaders();
//        //区分是正常的数据响应还是文件下载
//        for (Pair<String, String> pair : headerList) {
//            if (pair.first().equals("Content-Type")) {
//                System.err.println(pair.second());
//                if (pair.second().contains("image")){
//                    return null;
//                }
//            }
//        }
        if (stream != null) {
            try {
                outBody = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
                if (outBody != null && !"".equals(outBody)) {
                    try {
                        JSONObject jsonObj = JSONObject.parseObject(outBody);
                        Object code = jsonObj.get("code");
                        Object msg = jsonObj.get("msg");
                        sysLog.setOpResult(code.toString());
                        sysLog.setOpResultInfo(msg.toString());
                        sysLog.setCheckDigit(MD5Utils.encryption(sysLog.toStringForCheck()));
                        sysLogService.insertSelective(sysLog);
                    } catch (Exception e) {
                        logger.warn("path[" + path + "]返回的不是json格式数据");
                        Response res = new Response(ResultCodeEnum.ServiceDataFormatException);
                        sysLog.setOpResult(res.getCode().toString());
                        sysLog.setOpResultInfo(res.getMsg());
                        ctx.setResponseBody(res.toJsonString());
                        sysLog.setCheckDigit(MD5Utils.encryption(sysLog.toStringForCheck()));
                        sysLogService.insertSelective(sysLog);
                        ctx.setResponseBody(outBody);
//                        ctx.setResponseDataStream(stream);
                        return null;
                    }
                }
            } catch (Exception e) {
                logger.error("StreamUtils.copyToString异常：" + e.getMessage());
            }
        }
        ctx.setResponseBody(outBody);
        return null;
    }


}
*/
