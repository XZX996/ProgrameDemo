package com.example.nacos_server.nacos;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class NacosRegisterConfiguration {

    @Value("${server.port}")
    private int serverPort;

    @Value("${spring.application.name}")
    private String applicationName;

    @NacosInjected
    private NamingService namingService;

    @PostConstruct
    public void registerInstance() throws NacosException {
        namingService.registerInstance(applicationName, "11.101.4.197", serverPort, "DEFAULT");
    }
}

