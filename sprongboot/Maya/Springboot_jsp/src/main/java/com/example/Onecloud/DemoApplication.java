package com.example.Onecloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;


//@EnableEurekaClient //只适用于注册中心是eureka;
@EnableDiscoveryClient
@EnableEurekaServer   //启动服务注册
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.Onecloud.*"})
@MapperScan(basePackages = {"com.example.Onecloud.mapper"})
/*开启缓存*/
@EnableScheduling
@EnableCaching
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	MultipartConfigElement multipartConfigElement(){
		MultipartConfigFactory ConfigFactory=new MultipartConfigFactory();
		ConfigFactory.setLocation("D:/JavaDemo/Maya/Springboot_demo/src/main/webapp/upload");
		return ConfigFactory.createMultipartConfig();
	}


}
