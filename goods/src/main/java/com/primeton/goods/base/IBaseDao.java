package com.primeton.goods.base;

import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {

	void add(T t);
	
	void delete(T t);

	void update(T t);
	
	int count();
	
}
