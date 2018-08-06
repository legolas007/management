package com.primeton.goods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class GoodsApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(GoodsApplication.class);

    @Autowired
    private DiscoveryClient discoveryClient;

	@GetMapping("/serviceB")
	public String serviceB(){
		LOGGER.info("serviceB");
        return "Services:" + discoveryClient.getServices();
	}
	public static void main(String[] args) {
		SpringApplication.run(GoodsApplication.class, args);
	}
}
