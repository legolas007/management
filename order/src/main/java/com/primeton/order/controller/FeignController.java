package com.primeton.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.primeton.order.FeignClient.FeignGoodsClient;
import com.primeton.order.vo.JsonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Usher
 * @Description:
 */
@RestController
@RequestMapping
public class FeignController {

    @Autowired
    private FeignGoodsClient feignGoodsClient;

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/findBookById/{id}")
    public JsonBean findBook(@PathVariable("id") Integer id) {
        return feignGoodsClient.findBookById(id);
    }

    private String fallback() {
        return "It's too busy!Please try it later!";
    }

}
