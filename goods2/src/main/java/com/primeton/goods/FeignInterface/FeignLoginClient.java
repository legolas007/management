package com.primeton.goods.FeignInterface;

import com.primeton.goods.vo.JsonBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Usher
 * @Description:
 */
@FeignClient(value = "user")
public interface FeignLoginClient {

    @PostMapping(value = "/proxyLogin")
    JsonBean proxyLogin(@RequestParam("userName") String username, @RequestParam("passWord") String password, HttpServletResponse response);

    @RequestMapping("/validata")
    JsonBean validata(HttpServletRequest request);

    @GetMapping("/hello")
    String hello();
}
