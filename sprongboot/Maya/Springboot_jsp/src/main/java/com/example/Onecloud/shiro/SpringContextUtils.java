/*
package com.example.Onecloud.shiro;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

*/
/**
 * spring上下文工具类，用于普通类调用springIOC中的对象
 * **//*

@Component
public  class SpringContextUtils implements ApplicationContextAware {
    public static ApplicationContext context;
    public static ApplicationContext getContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.context = applicationContext;
    }
}
*/
