package com.primeton.goods.controller;

import com.primeton.goods.FeignInterface.FeignLoginClient;
import com.primeton.goods.util.JsonToObject;
import com.primeton.goods.vo.JsonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Usher
 * @Description:
 */
@RestController
@RequestMapping
public class FeignController {

    @Autowired
    private FeignLoginClient feignLoginClient;

    @PostMapping("/login")
    public JsonBean login(@RequestParam("userName") String username, @RequestParam("passWord") String password,HttpServletResponse response) {
        return feignLoginClient.proxyLogin(username, password, response);
    }


    @GetMapping("/feignLogin")
    public String hello() {
        return feignLoginClient.hello();
    }
}
