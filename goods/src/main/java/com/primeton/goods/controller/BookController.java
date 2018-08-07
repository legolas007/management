package com.primeton.goods.controller;

import com.primeton.goods.entity.TBook;
import com.primeton.goods.service.BookService;
import com.primeton.goods.vo.JsonBean;
import com.primeton.goods.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Usher
 * @Description:
 */
@RestController
@RequestMapping
@CrossOrigin
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books/page/{page}")
    public JsonBean findByPage(@PathVariable("page") Integer page){

        JsonBean bean = new JsonBean();

        try {
            PageBean<TBook> infos = bookService.findByPage(page);
            bean.setCode(1);
            bean.setMsg(infos);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            bean.setCode(0);
            bean.setMsg(e.getMessage());
        }

        return bean;

    }
}
