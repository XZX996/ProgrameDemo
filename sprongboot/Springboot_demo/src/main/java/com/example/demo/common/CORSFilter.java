/*
package com.example.demo.common;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
@Component
@WebFilter(filterName = "CORSFilter", urlPatterns = "/*")
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) res;
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        // 允许哪些Origin发起跨域请求,nginx下正常
        httpResponse.setHeader( "Access-Control-Allow-Origin", "*" );
        //httpResponse.setHeader("Access-Control-Allow-Origin", httpRequest.getHeader("Origin"));
        // 允许请求的方法
        httpResponse.setHeader( "Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT" );
        //httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getMethod());
        // 多少秒内，不需要再发送预检验请求，可以缓存该结果
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        // 表明它允许跨域请求包含xxx头
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Authorization,JSESSIONID");
        //是否允许浏览器携带用户身份信息（cookie）
        httpResponse.setHeader( "Access-Control-Allow-Credentials", "true" );
        //使前端能够获取到
        //httpResponse.setHeader("Access-Control-Expose-Headers", "download-status,download-filename,download-message");
        httpResponse.setHeader( "Access-Control-Expose-Headers", "*" );

        if (httpRequest.getMethod().equals("OPTIONS")) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }

    }

    @Override
    public void destroy() {

    }
}
*/
