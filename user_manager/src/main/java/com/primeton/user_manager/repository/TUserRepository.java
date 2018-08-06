package com.primeton.user_manager.repository;

import com.primeton.user_manager.entity.TUser;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

/**
 * @Author: Usher
 * @Description:
 */
public interface TUserRepository extends JpaRepository<TUser,Integer> {
    TUser findByUsername(String name);

    void deleteTUserById(Integer id);
}
