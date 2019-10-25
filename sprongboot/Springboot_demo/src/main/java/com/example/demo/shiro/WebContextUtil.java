package com.example.demo.shiro;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 文件描述：获取Web容器上下文工具类
 */
public  class WebContextUtil {

    public static HttpSession session;
    /**
     * 功能描述：获取请求Request对象
     * @return 请求的HttpServletRequest实例
     */
    public static HttpServletRequest getHttpServletRequest() {
        HttpServletRequest re=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        session = re.getSession();
//        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return re;
    }

}
