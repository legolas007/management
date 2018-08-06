package com.primeton.user_manager.controller;

import com.primeton.user_manager.dto.TUserDto;
import com.primeton.user_manager.entity.TUser;
import com.primeton.user_manager.service.impl.LoginServiceImpl;
import com.primeton.user_manager.vo.JsonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Usher
 * @Description:
 */

@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    public JsonBean login(@RequestParam("userName")String username, @RequestParam("passWord")String password, HttpServletResponse response){

        JsonBean bean = new JsonBean();

        try {
            loginService.login(username, password);

            Cookie cookie = new Cookie("loginname", username);
            cookie.setMaxAge(30 * 24 * 3600);
            response.addCookie(cookie);

            cookie = new Cookie("cartInfo", "");
            cookie.setMaxAge(30 * 24 * 3600);
            response.addCookie(cookie);

            bean.setCode(1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            bean.setCode(0);
            bean.setMsg(e.getMessage());
        }

        return bean;
    }

    @PostMapping ("/register")
    public JsonBean register(TUserDto user){
        JsonBean bean = new JsonBean();

        try {
            loginService.register(user);
            bean.setCode(1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            bean.setCode(0);
            bean.setMsg(e.getMessage());
        }

        return bean;
    }

    @RequestMapping("/logout")
    public JsonBean logout(HttpServletRequest request, HttpServletResponse response){
        JsonBean bean = new JsonBean();
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("loginname") || cookie.getName().equals("cartInfo")){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        bean.setCode(1);
        return bean;
    }

}
