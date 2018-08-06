package com.primeton.user_manager.service.impl;

import com.primeton.user_manager.dao.IUserDao;
import com.primeton.user_manager.entity.TUser;
import com.primeton.user_manager.repository.TUserRepository;
import com.primeton.user_manager.service.UserService;
import com.primeton.user_manager.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Usher
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private TUserRepository repository;

    @Override
    public void unlockUser(int id) {

        try {
            userDao.updateLockStatus(id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw e;
        }
    }

    //分页
    @Override
    public PageBean<TUser> findAllUser(int currentPage) {
        PageBean<TUser> bean = new PageBean<>();


        int size = bean.getSize();

        int count = 0;
        try {
            count = userDao.count();
            bean.setCount(count);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw e;
        }

        int totalPage = 0;
        if(count % size == 0) {
            totalPage = count / size;
        }else {
            totalPage = count / size + 1;
        }
        bean.setTotalPage(totalPage);


        int current = 0;
        if(currentPage <= 1) {
            current = 1;
        }else if(currentPage >= totalPage) {
            current = totalPage;
        }else {
            current = currentPage;
        }
        bean.setCurrentPage(current);



        Map<String, Object> map = new HashMap<>();
        int index = (current - 1) * size;
        map.put("index", index);
        map.put("size", size);
        List<TUser> list = null;
        try {
            list = userDao.findAll(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw e;
        }
        bean.setPageInfos(list);


        // TODO Auto-generated method stub
        return bean;
    }

    @Override
    public void deleteUser(Integer userId) {
        try {
            repository.deleteTUserById(userId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw e;
        }
    }
}
