package com.xzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


//@MapperScan("com.xzx.job_monitor.mapper")
//@ComponentScan(basePackages = {"com.stardon.job_monitor.*"})
@SpringBootApplication
@ServletComponentScan
public class ActivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityApplication.class, args);
	}

}
