package com.primeton.user_manager.controller;

import com.primeton.user_manager.entity.TUser;
import com.primeton.user_manager.service.UserService;
import com.primeton.user_manager.vo.JsonBean;
import com.primeton.user_manager.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Usher
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("userList/{page}")
    public JsonBean findAllUser(@PathVariable("page")int currentPage) {
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
    public JsonBean unlockUser(@PathVariable("id") int id) {
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
    public JsonBean deleteUser(@PathVariable("id") int id) {
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
