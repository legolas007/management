package com.primeton.goods.service;

import com.primeton.goods.entity.TBook;
import com.primeton.goods.vo.PageBean;

/**
 * @Author: Usher
 * @Description:
 */
public interface BookService {
    PageBean<TBook> findByPage(Integer page);
    public PageBean<TBook> findBookByIndex(int cur);

    public int deleteBook(Integer id);

    public void addBook(TBook book);

    public TBook findBookById(Integer id);

    public void updateBookWithRes(Integer resBookId, TBook book);

}
