package com.primeton.goods.base;

public interface IBaseDao<T> {

	void add(T t);
	
	void delete(T t);

	void update(T t);
	
	int count();
	
}
