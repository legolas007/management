package com.primeton.user_manager.service;


import com.primeton.user_manager.dto.TUserDto;
import com.primeton.user_manager.entity.TUser;

/**
 * @Author: Usher
 * @Description:
 */
public interface LoginService {
    void login(String name, String password);

    void register(TUserDto user);

    boolean userIsExist(String name);
}
