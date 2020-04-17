package com.example.servercloud.zuulRoute;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.CompositeRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class DynamicRoutesProcessor implements BeanFactoryPostProcessor, EnvironmentAware, ApplicationContextAware, PriorityOrdered {

    private static final String ZUUL_PROPERTY_SOURCE = "custom.zuul.routes";
    private ConfigurableEnvironment environment;
    private ApplicationContext applicationContext;
    private MapPropertySource routePropertySource = null;

    @Autowired
    private CompositeRouteLocator compositeRouteLocator;

    // 初始化路由
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        MutablePropertySources propertySources = environment.getPropertySources();

        // 可以从任何地方加载数据, 如: DB、Redis、配置中心等, 下面做示例数据
        Map<String, Object> data = new HashMap<>();
        data.put("zuul.routes.service4.path", "/api/service4/**");
        data.put("zuul.routes.service4.serviceId", "service4");

        routePropertySource = new MapPropertySource(ZUUL_PROPERTY_SOURCE, data);

        // 设置最高优先级
        propertySources.addFirst(routePropertySource);
    }

    // 动态刷新
    public void refreshRoutes(List<ZuulProperties.ZuulRoute> routeList) {
        // 提取 routeList 数据并覆盖到 routePropertySource

        // 将 @ConfigurationProperties 标记的类重新与PropertySources绑定, 包含ZuulProperties
        applicationContext.publishEvent(new EnvironmentChangeEvent(new HashSet<>()));

        // 刷新路由, 也可以直接调用 compositeRouteLocator.refresh()
        applicationContext.publishEvent(new RoutesRefreshedEvent(compositeRouteLocator));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void setEnvironment(Environment environment) {

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
