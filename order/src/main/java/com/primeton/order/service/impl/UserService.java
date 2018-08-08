package com.primeton.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primeton.order.dao.IUserDao;
import com.primeton.order.entity.User;
import com.primeton.order.service.IUserService;
import com.primeton.order.vo.PageBean;

@Service
public class UserService implements IUserService{

	@Autowired
	private IUserDao userDao;
	
	@Override
	public void unlockUser(int id) {
		// TODO Auto-generated method stub
		
		try {
			userDao.updateLockStatus(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
		
	}

	@Override
	public PageBean<User> findAllUser(int currentPage) {
		
		PageBean<User> bean = new PageBean<>();
		
		
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
		List<User> list = null;
		try {
			list = userDao.findAll(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		bean.setPageInfo(list);
		
		
		// TODO Auto-generated method stub
		return bean;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		try {
			userDao.delete(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}



}
