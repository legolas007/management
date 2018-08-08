package com.primeton.order.service;

import com.primeton.order.entity.Book;
import com.primeton.order.vo.PageBean;

public interface IBookService {

	public PageBean<Book> findBookByIndex(int cur);
	
	public int deleteBook(Integer id);
	
	public void addBook(Book book);
	
	public Book findBookById(Integer id);
	
	public void updateBookWithRes(Integer resBookId, Book book);
	
}
