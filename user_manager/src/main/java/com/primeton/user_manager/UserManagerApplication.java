package com.primeton.user_manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class UserManagerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserManagerApplication.class);

	@GetMapping("/serviceA")
	public String serviceA(){
		LOGGER.info("serviceA");
		return "serviceA";
	}


	public static void main(String[] args) {
		SpringApplication.run(UserManagerApplication.class, args);
	}
}
