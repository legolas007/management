package com.primeton.order.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {

	public List<T> findAll(Map<String, Object> pageInfo);
	
	public int count();
	
	public void add(T t);
	
	public void update(T t);
	
	public void delete(Integer id);
	
}
