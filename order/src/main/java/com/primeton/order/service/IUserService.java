package com.primeton.order.service;

import com.primeton.order.entity.User;
import com.primeton.order.vo.PageBean;

public interface IUserService {

	public void unlockUser(int id);
	
	public PageBean<User> findAllUser(int currentPage);
	
	public void deleteUser(Integer userId);
	
}
