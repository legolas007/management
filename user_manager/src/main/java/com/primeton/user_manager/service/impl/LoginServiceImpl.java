package com.primeton.user_manager.service.impl;

import com.primeton.user_manager.dao.IUserDao;
import com.primeton.user_manager.dto.TUserDto;
import com.primeton.user_manager.entity.TUser;
import com.primeton.user_manager.repository.TUserRepository;
import com.primeton.user_manager.service.LoginService;
import com.primeton.user_manager.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Usher
 * @Description:
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TUserRepository repository;

    @Autowired
    private IUserDao userDao;

    @Override
    public void login(String name, String password) {
        if (StringUtil.isNullOrEmpty(name)) {
            throw new RuntimeException("用户名不能为空");
        }

        if (StringUtil.isNullOrEmpty(password)) {
            throw new RuntimeException("密码不能为空");
        }

        TUser user;
        try {
            user = repository.findByUsername(name);
        } catch (Exception e) {
            throw e;
        }
        if(user == null){
            throw new RuntimeException("用户不存在!");
        }else{

            if(user.getIslock() >= 3) {
                throw new RuntimeException("用户不存在!");
            }


            if(!user.getPassword().equals(password)){
                int isLock = user.getIslock() + 1;
                try {
                    Map<String, Object> info = new HashMap<>();
                    info.put("id", user.getId());
                    info.put("islock", isLock);
                    userDao.updateStatus(info);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    throw e;
                }
                //TODO
                String msg = "!";
                msg += "����" + (3 -user.getIslock()) + "�λ���!";
                throw new RuntimeException(msg);

            }

            //�û��ɹ���¼���������������
            try {
                Map<String, Object> info = new HashMap<>();
                info.put("id", user.getId());
                info.put("islock", 0);
                userDao.updateStatus(info);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
    }

    @Override
    public void register(TUserDto user) {

        // TODO Auto-generated method stub
        if(user == null){
            throw new RuntimeException("用户不能为空");
        }


        if(!user.getPassword().equals(user.getRePassWord())){
            throw new RuntimeException("密码不一样");
        }
        TUser tUser = new TUser();
        tUser.setId(user.getId());
        tUser.setEmail(user.getEmail());
        tUser.setIslock(user.getIslock());
        tUser.setPassword(user.getPassword());
        tUser.setUsername(user.getUserName());
        try {
            if(!userIsExist(user.getUserName())){
                repository.save(tUser);
            }else{
                throw new RuntimeException("用户已存在");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw e;
        }
    }

    @Override
    public boolean userIsExist(String name) {
        try {
            TUser user = repository.findByUsername(name);
            return user != null;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw e;
        }
    }
}
