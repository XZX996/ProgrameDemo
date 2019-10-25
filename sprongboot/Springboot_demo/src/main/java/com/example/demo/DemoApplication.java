package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication

@ComponentScan(basePackages = {"com.example.demo.*"})
@MapperScan(basePackages = {"com.example.demo.Dao"})
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
