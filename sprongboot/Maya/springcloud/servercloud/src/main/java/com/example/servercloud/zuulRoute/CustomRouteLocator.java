package com.example.servercloud.zuulRoute;


import com.example.servercloud.pojo.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    public final static Logger logger = LoggerFactory.getLogger(CustomRouteLocator.class);


    private ZuulProperties properties;

    /*
     * 服务器配置mapper
     * */


    //@Resource
    //private ServersMapper serversMapper;

    public CustomRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
        logger.info("servletPath:{}", servletPath);
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        //从application.properties中加载路由信息
        //routesMap.putAll(super.locateRoutes());
        //从db中加载路由信息
        try {
            routesMap.putAll(locateRoutesFromDB());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //优化一下配置
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            // Prepend with slash if not already present.
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return values;
    }

    private Map<String, ZuulProperties.ZuulRoute> locateRoutesFromDB() throws Exception {
        Map<String, ZuulProperties.ZuulRoute> routeMap = properties.getRoutes();
        service  ser =new service();
        List<service> data = ser.selectAll();
        ZuulProperties.ZuulRoute zuulRoute;
        // IP "ip", PORT "port", NAME "name", DESCR "descr"
        for (service map : data) {
            zuulRoute = new ZuulProperties.ZuulRoute();
            String path = map.getPath()==null?"/" + map.getName() + "/**":map.getPath();
            zuulRoute.setId(map.getName());
            zuulRoute.setPath(path);
            if(map.getIp()!=null && map.getPort()!=null) {
                String url = new StringBuilder().append("http://")
                        .append(map.getIp()).append(":").append(map.getPort()).append(path).toString();
                zuulRoute.setUrl(url);
            }else{
                zuulRoute.setServiceId(map.getName());
            }
            zuulRoute.setRetryable(false);
            zuulRoute.setStripPrefix(true);
            zuulRoute.setSensitiveHeaders(new HashSet<>());
            routeMap.put(map.getName(), zuulRoute);
        }
        //properties.setRoutes(routeMap);
        return routeMap;
    }


}
