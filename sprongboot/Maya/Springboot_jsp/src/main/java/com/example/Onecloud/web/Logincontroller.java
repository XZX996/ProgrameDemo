package com.example.Onecloud.web;

import com.example.Onecloud.mapper.userMapper;
import com.example.Onecloud.pojo.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description:登录控制器
 * @version:1.0
 * @author:xzx
 * @date:2019/4/26
 */
@Controller
public class Logincontroller {

    private userMapper userMapper;

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public JsonResult notLogin() {
        return new JsonResult().failure(JsonResult.Meta.Err1,"需要登录");

    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public JsonResult notRole() {
        return new JsonResult().success("无权限");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public JsonResult logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return new JsonResult().success("成功注销");
    }
    @GetMapping("/login")
    public String login() throws Exception {
        return "login";
    }
    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(String username, String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        String role = "ss";//userMapper.getRole(username);
        if ("2".equals(role)) {
            return new JsonResult().success("欢迎登陆");
        }
        if ("1".equals(role)) {
            return new JsonResult().success("欢迎来到管理员页面");
        }
        return new JsonResult().failure(JsonResult.Meta.Err3,"权限错误！");
    }
}