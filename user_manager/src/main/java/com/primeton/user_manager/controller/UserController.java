package com.primeton.user_manager.controller;

import com.primeton.user_manager.entity.TUser;
import com.primeton.user_manager.repository.TUserRepository;
import com.primeton.user_manager.service.UserService;
import com.primeton.user_manager.vo.JsonBean;
import com.primeton.user_manager.vo.PageBean;
import com.sun.javafx.runtime.async.BackgroundExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Usher
 * @Description:
 */
@RestController
@RequestMapping("/userManager")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TUserRepository repository;

    @GetMapping("userList/{page}")
    public JsonBean findAllUser(@PathVariable("page")int currentPage, HttpServletRequest request) {
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
                    if (user.getIslock() != 0) {
                        JsonBean bean = new JsonBean();
                        bean.setCode(0);
                        bean.setMsg("请登录！");
                        return bean;
                    }

                }
            }

        }
        JsonBean bean = new JsonBean();

        try {
            PageBean<TUser> pageInfo = userService.findAllUser(currentPage);
            bean.setCode(1);
            bean.setMsg(pageInfo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            bean.setCode(0);
            bean.setMsg(e.getMessage());
        }

        return bean;
    }


    @PostMapping("unlock/{id}")
    public JsonBean unlockUser(@PathVariable("id") int id,HttpServletRequest request) {
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
                    if (user.getIslock() != 0) {
                        JsonBean bean = new JsonBean();
                        bean.setCode(0);
                        bean.setMsg("请登录！");
                        return bean;
                    }

                }
            }

        }
        JsonBean bean = new JsonBean();

        try {

            userService.unlockUser(id);
            bean.setCode(1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            bean.setCode(0);
            bean.setMsg(e.getMessage());
        }

        return bean;

    }

    @PostMapping("user/{id}")
    public JsonBean deleteUser(@PathVariable("id") int id,HttpServletRequest request) {
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
                    if (user.getIslock() != 0) {
                        JsonBean bean = new JsonBean();
                        bean.setCode(0);
                        bean.setMsg("请登录！");
                        return bean;
                    }

                }
            }

        }

        JsonBean bean = new JsonBean();
        try {
            userService.deleteUser(id);
            bean.setCode(1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            bean.setCode(0);
            bean.setMsg(e.getMessage());
        }

        return bean;

    }
}
