/*
package com.example.nacos_server.ZuulRoute;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.example.nacos_server.nacos.NacosRegisterConfiguration.NACOS_DATA_ID;

@Component
public class PropertiesAssemble{

    @Autowired
    private ConfigService configService;

    public Map<String, ZuulProperties.ZuulRoute> getProperties() {
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
        List<ZuulRouteEntity> results = listenerNacos(NACOS_DATA_ID,NACOS_GROUP_ID);
        for (ZuulRouteEntity result : results) {
            if (StringUtils.isBlank(result.getPath())
                */
/*|| org.apache.commons.lang3.StringUtils.isBlank(result.getUrl())*//*
) {
                continue;
            }
            ZuulRoute zuulRoute = new ZuulRoute();
            try {
                BeanUtils.copyProperties(result, zuulRoute);
            } catch (Exception e) {
            }
            routes.put(zuulRoute.getPath(), zuulRoute);
        }
        return routes;
    }




    private List<ZuulRouteEntity> listenerNacos (String dataId, String group) {
        try {
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println("从Nacos返回的配置：" + content);
            return JSONObject.parseArray(content, ZuulRouteEntity.class);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
*/
