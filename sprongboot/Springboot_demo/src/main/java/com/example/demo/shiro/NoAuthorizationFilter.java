package com.example.demo.shiro;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.JsonResult;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoAuthorizationFilter extends PermissionsAuthorizationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/json");
        JsonResult res = new JsonResult().failure(JsonResult.Meta.Err3);
        httpServletResponse.getWriter().write(JSONObject.toJSON(res).toString());
        return false;
    }
}
