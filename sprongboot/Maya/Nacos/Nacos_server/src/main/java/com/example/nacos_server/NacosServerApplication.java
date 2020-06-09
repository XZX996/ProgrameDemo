package com.example.nacos_server;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication
@NacosPropertySource(dataId = "Test1", autoRefreshed = true)
@EnableZuulProxy
public class NacosServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(NacosServerApplication.class, args);
    }

}
