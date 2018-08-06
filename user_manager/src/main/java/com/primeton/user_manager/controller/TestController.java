package com.primeton.user_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Usher
 * @Description:
 */
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String string() {
        return restTemplate.getForEntity("http://goods/serviceB", String.class).getBody();
    }
}
