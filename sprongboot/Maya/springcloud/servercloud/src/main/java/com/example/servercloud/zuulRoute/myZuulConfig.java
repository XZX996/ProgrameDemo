package com.example.servercloud.zuulRoute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * add by xzx
 * 把zuul配置改为从数据库获取
 */
@Configuration
public class myZuulConfig {

    @Autowired
    ZuulProperties zuulProperties;
    @Autowired
    ServerProperties server;
    /*
     * 服务器配置mapper
     * */

    @Bean
    public CustomRouteLocator routeLocator(){
        CustomRouteLocator routeLocator=new CustomRouteLocator("",this.zuulProperties);
        return routeLocator;
    }

    /*public SimpleRouteLocator zuulRouteLocator(ZuulProperties zuulProperties) throws Exception {


        Map<String, ZuulProperties.ZuulRoute> routeMap = zuulProperties.getRoutes();
        List<Map> data = serversMapper.listServers();
        ZuulProperties.ZuulRoute zuulRoute;
        // IP "ip", PORT "port", NAME "name", DESCR "descr"
        for (Map map : data) {
            zuulRoute = new ZuulProperties.ZuulRoute();
            String path = map.get("name").toString();
            zuulRoute.setId(path);
            zuulRoute.setPath("/" + path + "/**");
            String url = new StringBuilder().append("http://")
                    .append(map.get("ip")).append(":").append(map.get("port"))
                    .append("/").append(path).append("/").toString();
            zuulRoute.setUrl(url);
            zuulRoute.setRetryable(false);
            zuulRoute.setStripPrefix(true);
            zuulRoute.setServiceId(path);
            zuulRoute.setSensitiveHeaders(new HashSet<>());
            routeMap.put(path, zuulRoute);
        }
        zuulProperties.setRoutes(routeMap);
        return new SimpleRouteLocator("", zuulProperties);
    }*/
}
