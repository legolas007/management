package com.primeton.user_manager.controller;

import com.primeton.user_manager.dto.TUserDto;
import com.primeton.user_manager.entity.TUser;
import com.primeton.user_manager.repository.TUserRepository;
import com.primeton.user_manager.service.impl.LoginServiceImpl;
import com.primeton.user_manager.util.JsonUtil;
import com.primeton.user_manager.vo.JsonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Usher
 * @Description:
 */

@RestController
@RequestMapping
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    private TUserRepository repository;

    /**
     * 测试
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        return "sdsd";
    }

    @GetMapping("/validata")
    public JsonBean validata(HttpServletRequest request) {

        if (request.getCookies() == null) {
            JsonBean bean = new JsonBean();
            bean.setCode(0);
            bean.setMsg("请登录！");
            return bean;
        } else {
            Cookie[] cookies = request.getCookies();

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loginname")) {
                    TUser user = repository.findByUsername(cookie.getValue());
                    if (user.getIslock() == 0) {
                        JsonBean bean = new JsonBean();
                        bean.setCode(1);
                        return bean;
                    }

                }
            }
        }
        JsonBean bean = new JsonBean();
        bean.setCode(0);
        bean.setMsg("权限不够！");
        return bean;
    }
    @PostMapping("/proxyLogin")
    public  JsonBean proxyLogin(@RequestParam("userName")String username, @RequestParam("passWord")String password, HttpServletResponse response){

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
