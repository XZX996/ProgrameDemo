package com.example.demo.controller;

import com.example.demo.Dao.userMapper;
import com.example.demo.common.Iphelper;
import com.example.demo.common.OperationLog;
import com.example.demo.common.OperationType;
import com.example.demo.common.RetryLimitCredentialsMatcher;
import com.example.demo.pojo.JsonResult;
import com.example.demo.pojo.UserInfo;
import com.example.demo.shiro.WebContextUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:登录控制器
 * @version:1.0
 * @author:xzx
 * @date:2019/4/26
 */
@OperationLog(value = "分页查询通用参数", type = OperationType.QUERY, sysName = "业务系统")
@RestController
@RequestMapping("/login")
public class Logincontroller {


    @Autowired
    private userMapper userMapper;
    @Autowired
    private RetryLimitCredentialsMatcher credentialsMatcher;

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public JsonResult notLogin() {
        return new JsonResult().failure(JsonResult.Meta.Err1,"需要登录");

    }

    @RequestMapping(value = "/Unlock", method = RequestMethod.GET)
    public JsonResult Unlock(UserInfo user) {
        Map muser=new HashMap();
        muser.put("LOGINNAME",user.username);
        String passwordEncoded = new SimpleHash("md5",user.password1).toString();
        muser.put("PASSWORD",passwordEncoded);
        muser.put("ISLOCK",'0');
        if(credentialsMatcher.unlockAccount(muser)) {
            return new JsonResult().success("成功解锁");
        }else {
            return new JsonResult().failure(JsonResult.Meta.Err7);
        }

    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public JsonResult notRole() {
        return new JsonResult().failure(JsonResult.Meta.Err3,"无权限");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public JsonResult logout(HttpServletRequest request) {

        //Subject subject = SecurityUtils.getSubject();
       // String loginName = (String) subject.getPrincipal();
        //String IP= Iphelper.getIpAddr(httpServletRequest);
        //String ss=request.getHeader("Authorization");   //Authorization
        //判断是否异地登录 通过token和ip匹配比较
        //String tokenid = new SimpleHash("md5",user.username+user.password1).toString();
        String ss=request.getHeader("JSESSIONID");   //Authorization
        Subject subject = SecurityUtils.getSubject();
        String loginName = (String) subject.getPrincipal();
        if(ss!=null &&(loginName!=null || loginName !="")) {
            //注销
            WebContextUtil.session=null;
            subject.logout();

            return new JsonResult().success("成功注销");
        }else{
            return new JsonResult().success("注销异常");
        }
    }
    /**
     * 登陆
     *
     * @param
     */
    @OperationLog(value = "分页查询通用参数", type = OperationType.QUERY, sysName = "业务系统")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(ServletRequest request, UserInfo user) throws Exception {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(user.username, user.password1);
        //是否记住用户
        if(user.remember>0){
            token.setRememberMe(true);
        }
        String tokenid="";
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String IP= Iphelper.getIpAddr(httpServletRequest);
        //String ss=request.getHeader("Authorization");   //Authorization
        //判断是否异地登录 通过token和ip匹配比较
        tokenid = new SimpleHash("md5",user.username+user.password1).toString();
        String JSESSIONID = subject.getSession().getId().toString();
        if(WebContextUtil.session!=null) {
            Object info = WebContextUtil.session.getAttribute(JSESSIONID);
            //Object user = SecurityUtils.getSubject().getPrincipal();
            if (info != IP && info!=null) {
                WebContextUtil.session.removeAttribute(JSESSIONID);
                throw new Exception("重复登陆");
            }
        }
        // 执行认证登陆
        try {
            subject.login(token);
            //Session session = subject.getSession();
            //存登陆的用户的信息到session
            WebContextUtil.getHttpServletRequest();
            //设置不活跃 30分钟失效
            WebContextUtil.session.setMaxInactiveInterval(30*60);
            //获取IP 拼接token
            WebContextUtil.session.setAttribute(JSESSIONID,IP);
            //session.setAttribute(user.username+"012",user);
        } catch (Exception e) {
             throw e;
        } finally {
        }
        //根据权限，指定返回数据
        String role = userMapper.getRole(user.username);
        if ("2".equals(role)) {
            return new JsonResult().success(JSESSIONID);
        }
        if ("1".equals(role)) {
            return new JsonResult().success(JSESSIONID);
        }

        return new JsonResult().failure(JsonResult.Meta.Err3,"权限错误！");
    }

    public JsonResult register()throws Exception{

        return new JsonResult().failure(JsonResult.Meta.Err3,"权限错误！");
    }
}
