package com.example.demo.shiro;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.Iphelper;
import com.example.demo.pojo.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 自定义authc拦截器，不拦截跨域OPTIONS请求
 */
public class NoAuthenticationFilter extends FormAuthenticationFilter {


	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		//String ss=httpServletRequest.getHeader("JSESSIONID");   //Authorization

		DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
		MySessionManager sessionManager = (MySessionManager) securityManager.getSessionManager();
		Serializable SessionId = sessionManager.getSessionId(request, response);
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.setContentType("text/json");

		if (SessionId == null) {
			JsonResult res = new JsonResult().failure(JsonResult.Meta.Err1);
			httpServletResponse.getWriter().write(JSONObject.toJSON(res).toString());
		}
		return false;
		//return super.onAccessDenied(request, response);
		/*if (httpServletRequest.getMethod().equals("OPTIONS")) {
			return true;
		}*/
		/*return super.onAccessDenied(request, response);*/
	}
}
