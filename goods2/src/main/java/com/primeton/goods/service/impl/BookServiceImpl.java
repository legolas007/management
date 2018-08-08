package com.primeton.goods.service.impl;

import com.primeton.goods.dao.IBookDao;
import com.primeton.goods.entity.TBook;
import com.primeton.goods.service.BookService;
import com.primeton.goods.service.ComUtil;
import com.primeton.goods.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Usher
 * @Description:
 * 后台CRUD
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private IBookDao bookDao;

    @Override
    public PageBean<TBook> findByPage(Integer page) {
        if(page == null || page < 1){
            throw new RuntimeException("没有数据！");
        }

        PageBean<TBook> pageBean = new PageBean<>();
        pageBean.setCurrentPage(page);

        int count = 0;
        try {
            count = bookDao.count();
            pageBean.setCount(count);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            throw e1;
        }

        if(count % pageBean.getSize() == 0){
            pageBean.setTotalPage(count / pageBean.getSize());
        }else {
            pageBean.setTotalPage(count / pageBean.getSize() + 1);
        }


        Map<String, Object> map = new HashMap<>();
        map.put("size", pageBean.getSize());
        int index = (page-1)*pageBean.getSize();
        map.put("index", index);

        try {
            List<TBook> books = bookDao.findByIndex(map);
            pageBean.setPageInfos(books);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
        return pageBean;
    }

    @Override
    public PageBean<TBook> findBookByIndex(int cur) {

        PageBean<TBook> bean = new PageBean<>();

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
        List<TBook> list = null;
        try {
            list = bookDao.findAll(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw e;
        }
        bean.setPageInfos(list);
        return bean;
    }

    /**
     * 后台CRUD
     * @param id
     * @return
     */
    @Override
    public int deleteBook(Integer id) {
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
    public void addBook(TBook book) {
        try {
            bookDao.add(book);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public TBook findBookById(Integer id) {
        TBook book = null;

        try {
            book = bookDao.findById(id);
        } catch (Exception e) {
            throw e;
        }


        if(book == null) {
            throw new RuntimeException("图书不存在");
        }

        return book;
    }

    @Override
    public void updateBookWithRes(Integer resBookId, TBook book) {
        TBook newBook = null;

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
