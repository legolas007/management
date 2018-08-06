package com.primeton.user_manager.service;

import com.primeton.user_manager.entity.TUser;
import com.primeton.user_manager.vo.PageBean;

/**
 * @Author: Usher
 * @Description:
 */
public interface UserService {
    void unlockUser(int id);

    PageBean<TUser> findAllUser(int currentPage);

    void deleteUser(Integer userId);
}
