package com.primeton.user_manager.repository;

import com.primeton.user_manager.entity.TUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: Usher
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TUserRepositoryTest {

    @Autowired
    private TUserRepository repository;

    @Test
    public void findByUserName() {
        TUser user;
        user = repository.findByUsername("usher");
        System.out.println(user);
    }
}