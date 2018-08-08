package com.primeton.order.dao;

import com.primeton.order.entity.Book;
import com.primeton.order.entity.Books;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IBookDao extends IBaseDao<Book>{

	public void deleteBook(Integer id);
	
	public Book findById(Integer id);

	List<Books> findByIds(String[] split);
}
