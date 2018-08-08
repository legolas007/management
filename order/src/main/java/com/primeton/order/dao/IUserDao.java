package com.primeton.order.dao;

import com.primeton.order.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IUserDao extends IBaseDao<User> {

	public void updateLockStatus(int id);

	User findByName(String name);
}
