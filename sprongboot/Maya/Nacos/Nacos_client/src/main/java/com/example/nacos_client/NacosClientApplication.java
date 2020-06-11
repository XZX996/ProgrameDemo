package com.example.nacos_client;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import net.hasor.spring.boot.EnableHasor;
import net.hasor.spring.boot.EnableHasorWeb;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;


//@EnableHasor()
//@EnableHasorWeb()
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.nacos_client.*"})
@MapperScan("com.example.nacos_client.Mapper")
//@NacosPropertySource(dataId = "Test1", autoRefreshed = true)
public class NacosClientApplication {

    public static void main(String[] args) {

        SpringApplication.run(NacosClientApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }


}
