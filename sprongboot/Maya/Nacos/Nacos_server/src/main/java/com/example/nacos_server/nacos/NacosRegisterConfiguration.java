package com.example.nacos_server.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.example.nacos_server.ZuulRoute.CustomRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.concurrent.Executor;

@Configuration
public class NacosRegisterConfiguration {

    public static  String NACOS_DATA_ID="zuul_service";
    public static  String NACOS_GROUP_ID="zuul_route";

    @Value("${server.port}")
    private int serverPort;
    @Value("${spring.cloud.nacos.config.server-addr}")
    private String serverAddr;

    @Value("${spring.application.name}")
    private String applicationName;

    @NacosInjected
    private NamingService namingService;

    @Autowired
    CustomRouteLocator routeLocator;

    @Autowired
    ApplicationEventPublisher publisher;

    @PostConstruct
    public void registerInstance() throws NacosException {
        namingService.registerInstance(applicationName, "127.0.0.1", serverPort, "DEFAULT");
    }

    @Bean
    public ConfigService configService(){
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        try {
            ConfigService configService = NacosFactory.createConfigService(properties);
            configService.addListener(NACOS_DATA_ID, NACOS_GROUP_ID, new Listener() {
                @Override
                public Executor getExecutor() {
                    //可以发送监听消息到某个MQ
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("Nacos更新了！");
                    //切忌！！！不需要自己去刷新
                    RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
                    publisher.publishEvent(routesRefreshedEvent);
                }
            });

            return configService;
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }
}

