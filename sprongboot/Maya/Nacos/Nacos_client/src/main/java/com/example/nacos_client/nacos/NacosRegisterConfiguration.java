package com.example.nacos_client.nacos;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
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
        String serviceName = "server";
        String groupName = "web";
        Instance instance = new Instance();
        instance.setIp("127.0.0.1");
        instance.setPort(serverPort);
        instance.setHealthy(true);
        //配置了权重。权重为浮点数，值越大，分配给该实例的流量越大
        instance.setWeight(1.0);

        namingService.registerInstance(applicationName, groupName, instance);
    }
}

