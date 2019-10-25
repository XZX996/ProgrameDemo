package com.example.demo.shiro;

import com.example.demo.common.Iphelper;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 自定义authc拦截器，不拦截跨域OPTIONS请求
 */
public class NoAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String ss=httpServletRequest.getHeader("ticket");   //Authorization

		if(WebContextUtil.session!=null) {
			Object info = WebContextUtil.session.getAttribute(ss);
			String IP= Iphelper.getIpAddr(httpServletRequest);
			//Object user = SecurityUtils.getSubject().getPrincipal();
			if (info == IP) {
				return true;
			}
		}
		return super.onAccessDenied(request, response);
		/*if (httpServletRequest.getMethod().equals("OPTIONS")) {
			return true;
		}*/

		/*return super.onAccessDenied(request, response);*/
	}
}
