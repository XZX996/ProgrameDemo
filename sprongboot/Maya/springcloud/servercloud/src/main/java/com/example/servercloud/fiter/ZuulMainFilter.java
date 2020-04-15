/*
package com.example.servercloud.fiter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.stardon.stardon_main.comm.Response;
import com.stardon.stardon_main.comm.ResponseUtil;
import com.stardon.stardon_main.comm.ResultCodeEnum;
import com.stardon.stardon_main.pojo.SysServers;
import com.stardon.stardon_main.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class ZuulMainFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ISysServersService sysServersService;
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
        return "pre";
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
        SysServers sysServers = null;
        try {
            URL url = new URL(request.getRequestURL().toString());
            path = url.getPath();
            sysServers = sysServersService.selectByPrimaryKey(path.split("/")[1]);
            if (sysServers != null) {
                if (sysServers.getState().equals("1")) {
                    ctx.setSendZuulResponse(true);  //true-进行路由
                    RequestContext requestContext = RequestContext.getCurrentContext();
                    requestContext.addZuulRequestHeader("stardon-ser-key", sysServers.getSerKey());
                } else {
                    ctx.setSendZuulResponse(false);
                    ResponseUtil.writeJson(response, new Response(ResultCodeEnum.ServiceDisable).toJsonString());
                }
            } else {
                ctx.setSendZuulResponse(false);
                ResponseUtil.writeJson(response, new Response(ResultCodeEnum.ServiceNotRegistered).toJsonString());
            }
        } catch (MalformedURLException e) {
            logger.error(request.getRequestURL().toString() + "解析失败");
            ctx.setSendZuulResponse(false);
            ResponseUtil.writeJson(response, new Response(ResultCodeEnum.ServiceURLIllegal).toJsonString());
        }
        return null;
    }


}
*/
