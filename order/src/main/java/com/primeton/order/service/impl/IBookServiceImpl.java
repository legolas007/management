package com.primeton.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primeton.order.dao.IBookDao;
import com.primeton.order.dao.IOrderItemDao;
import com.primeton.order.entity.Book;
import com.primeton.order.service.IBookService;
import com.primeton.order.util.ComUtil;
import com.primeton.order.vo.PageBean;

@Service
public class IBookServiceImpl implements IBookService {

	@Autowired
	private IBookDao bookDao;
	
	@Autowired
	private IOrderItemDao itemDao;
	
	@Override
	public PageBean<Book> findBookByIndex(int cur) {
		// TODO Auto-generated method stub
		
		PageBean<Book> bean = new PageBean<>();
		
		int size = bean.getSize();
		
		int count = 0;
		try {
			count = bookDao.count();
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
		if(cur <= 1) {
			current = 1;
		}else if(cur >= totalPage) {
			current = totalPage;
		}else {
			current = cur;
		}
		bean.setCurrentPage(current);
		
		
		
		Map<String, Object> map = new HashMap<>();
		int index = (current - 1) * size;
		map.put("index", index);
		map.put("size", size);
		List<Book> list = null;
		try {
			list = bookDao.findAll(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		bean.setPageInfo(list);
		
		
		return bean;
	}


	@Override
	public int deleteBook(Integer id) {
		// TODO Auto-generated method stub
		int count = 0;
		
		try {
			count = itemDao.countItemByBookId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		if(count > 0) return 0;
		
		try {
			bookDao.deleteBook(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		return 1;

		
	}


	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		
		try {
			bookDao.add(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		
	}

	
	//����bookId����ͼ��
	public Book findBookById(Integer id) {
		
		Book book = null;
		
		try {
			book = bookDao.findById(id);
		} catch (Exception e) {
			throw e;
		}
		
		
		if(book == null) {
			throw new RuntimeException("��ͼ�鲻���ڣ�");
		}
		
		return book;
	}


	//����Ԥ�����Ϣ���޸�ͼ����Ϣ
	@Override
	public void updateBookWithRes(Integer resBookId, Book book) {
		// TODO Auto-generated method stub
		
		Book newBook = null;
		
		newBook = findBookById(resBookId);
		
		if(!newBook.getBookname().equals(book.getBookname())) {
			newBook.setBookname(book.getBookname());
		}else if(!newBook.getPrice().equals(book.getPrice())) {
			newBook.setPrice(book.getPrice());
		}else if(!newBook.getStock().equals(book.getStock())) {
			newBook.setStock(book.getStock());
		}else if(!ComUtil.StringIsEmpty(book.getImg()) && !newBook.getImg().equals(book.getImg())) {
			newBook.setImg(book.getImg());
		}
		
		try {
			bookDao.update(newBook);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
	}

}
