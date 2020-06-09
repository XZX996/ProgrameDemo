package com.example.nacos_server.ZuulRoute;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.example.nacos_server.nacos.NacosRegisterConfiguration.NACOS_DATA_ID;
import static com.example.nacos_server.nacos.NacosRegisterConfiguration.NACOS_GROUP_ID;

public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    public final static Logger logger = LoggerFactory.getLogger(CustomRouteLocator.class);

    //@Autowired
    //private ISysGatewayApiDefineService sysGatewayApiDefineService;
    @Autowired
    private ZuulProperties properties;

    @Autowired
    private ConfigService configService;


    public CustomRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
        logger.info("servletPath:{}", servletPath);
    }

    //父类已经提供了这个方法，这里写出来只是为了说明这一个方法很重要！！！
    //    @Override
    //    protected void doRefresh() {
    //        super.doRefresh();
    //    }


    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        //从application.properties中加载路由信息
        routesMap.putAll(super.locateRoutes());
        //从db中加载路由信息
        //routesMap.putAll(locateRoutesFromDB());
        // 从Nacos中加载路由信息
        routesMap.putAll(locateRoutesFromNacos());
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

    public Map<String, ZuulProperties.ZuulRoute> locateRoutesFromNacos() {
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
        List<ZuulRouteVO> results = listenerNacos(NACOS_DATA_ID,NACOS_GROUP_ID);
        if(results!=null) {
            for (ZuulRouteVO result : results) {
                if (org.apache.commons.lang3.StringUtils.isBlank(result.getPath())
                    /*|| org.apache.commons.lang3.StringUtils.isBlank(result.getUrl())*/) {
                    continue;
                }
                ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
                try {
                    BeanUtils.copyProperties(result, zuulRoute);
                } catch (Exception e) {
                }
                routes.put(zuulRoute.getPath(), zuulRoute);
            }
        }
        return routes;
    }




    private List<ZuulRouteVO> listenerNacos (String dataId, String group) {
        try {
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println("从Nacos返回的配置：" + content);
            return JSONObject.parseArray(content, ZuulRouteVO.class);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /*private Map<String, ZuulProperties.ZuulRoute> locateRoutesFromDB() {
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
        List<SysGatewayApiDefine> results = sysGatewayApiDefineService.selectAll();

        List<ZuulRouteVO> ltZR = new ArrayList<ZuulRouteVO>();

        ZuulRouteVO zrvo = null;
        for (SysGatewayApiDefine item : results) {
            zrvo = new ZuulRouteVO();

            zrvo.setId(item.getId());
            zrvo.setPath(item.getPath());
            zrvo.setUrl(item.getUrl());
            if (item.getEnabled() == 1) {
                zrvo.setEnabled(true);
            } else {
                zrvo.setEnabled(false);
            }

            if (item.getStripPrefix() == 1) {
                zrvo.setStripPrefix(true);
            } else {
                zrvo.setStripPrefix(false);
            }
            zrvo.setServiceId(item.getServiceId());

            if (item.getRetryable() == 1) {
                zrvo.setRetryable(true);
            } else {
                zrvo.setRetryable(false);
            }
            ltZR.add(zrvo);
        }

        for (ZuulRouteVO result : ltZR) {
            if (StringUtils.isEmpty(result.getPath())) {
                continue;
            }
            if (StringUtils.isEmpty(result.getServiceId()) && StringUtils.isEmpty(result.getUrl())) {
                continue;
            }
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            try {
                BeanUtils.copyProperties(result, zuulRoute);
            } catch (Exception e) {
                logger.error("=============load zuul route info from db with error==============", e);
            }
            routes.put(zuulRoute.getPath(), zuulRoute);
        }
        return routes;
    }*/


}
