package com.example.servercloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaServer
//开启转发
@EnableZuulProxy
@MapperScan("com.example.servercloud.mapper")
public class ServercloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServercloudApplication.class, args);
	}

	/*@Bean
	public TokenFiter tokenFiter(){
		return new TokenFiter();
	}*/
}
