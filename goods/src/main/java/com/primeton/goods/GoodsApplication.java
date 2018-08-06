package com.primeton.goods;

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
public class GoodsApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(GoodsApplication.class);
	@GetMapping("/serviceB")
	public String serviceB(){
		LOGGER.info("serviceB");
		return "serviceB";
	}
	public static void main(String[] args) {
		SpringApplication.run(GoodsApplication.class, args);
	}
}
