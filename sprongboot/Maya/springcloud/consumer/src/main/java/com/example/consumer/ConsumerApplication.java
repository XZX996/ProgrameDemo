package com.example.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author Hero
 */
@EnableFeignClients
@SpringBootApplication
@EnableHystrix
public class ConsumerApplication {
	/**
	 * @LoadBalanced
	 *@Bean
	 * public RestTemplate restTemplate() {
	*	return new RestTemplate();
	*}
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
}
