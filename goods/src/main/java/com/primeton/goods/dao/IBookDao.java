package com.primeton.goods.dao;

import com.primeton.goods.base.IBaseDao;
import com.primeton.goods.entity.TBook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface IBookDao extends IBaseDao<TBook> {
	

	 List<TBook> findByIndex(Map<String, Object> pageInfo);
	void deleteBook(Integer id);

	 List<TBook> findByIds(List<String> ids);
	List<TBook> findByIds(String[] split);

	TBook findById(Integer id);

	void updateStock(Map<String, Object> stockInfo);
	List<TBook> findAll(Map<String, Object> map);
}
