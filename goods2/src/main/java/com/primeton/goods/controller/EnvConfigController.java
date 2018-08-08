package com.primeton.goods.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Usher
 * @Description:
 */
@RestController
@RequestMapping("/env")
@RefreshScope
public class EnvConfigController {
    @Value("${env}")
    private String env;

    @Value("${count}")
    private String count;
    @GetMapping("/count")
    public String getCount() {
        return count;
    }
    @GetMapping("/print")
    public String print() {
        return env;
    }

}
