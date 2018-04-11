package com.gzu.su.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
//@MapperScan("com.gzu.su.manager.*.dao")
public class ManagerApplication extends SpringBootServletInitializer {
 
	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}
}