package com.example.zipkinserver;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class ZipkinServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ZipkinServerApplication.class, args);
	}

	/**
	 * 配置抽样
	 * @return
	 */
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

}
