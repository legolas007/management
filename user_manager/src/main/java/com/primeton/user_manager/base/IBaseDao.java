package com.primeton.user_manager.base;

import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {

	public void add(T t);
	
	public void delete(T t);
	
	public void update(T t);
	


	public int count();
	
}
