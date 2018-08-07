package com.primeton.goods.controller;

import com.primeton.goods.FeignInterface.FeignLoginClient;
import com.primeton.goods.entity.TBook;
import com.primeton.goods.service.BookService;
import com.primeton.goods.vo.JsonBean;
import com.primeton.goods.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTML;
import java.io.File;

/**
 * @Author: Usher
 * @Description:
 */
@RestController
@CrossOrigin
public class CrudController {
    @Autowired
    private BookService bookService;

    @Autowired
    private FeignLoginClient feignLoginClient;

    @GetMapping("/bookdao")
    public TBook bookdao(Integer id) {
        return bookService.findBookById(id);
    }

    @GetMapping
    public String root() {
        return "redirect:/index.html";
    }

    @GetMapping("/bookList/{page}")
    public JsonBean findBookByIndex(@PathVariable("page")Integer cur, HttpServletRequest request) {
        JsonBean jsonBean = feignLoginClient.validata(request);
        if (jsonBean.getCode() == 0) {
            return jsonBean;
        }

        JsonBean bean = new JsonBean();

        try {
            PageBean<TBook> pageInfo = bookService.findBookByIndex(cur);
            bean.setCode(1);
            bean.setMsg(pageInfo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            bean.setCode(0);
            bean.setMsg(e.getMessage());
        }

        return bean;

    }

    @DeleteMapping("/deleteBook/{id}")
    public JsonBean deleteBook(@PathVariable("id")Integer id) {
        JsonBean bean = new JsonBean();

        int flag = 0;
        try {
            flag = bookService.deleteBook(id);

            if(flag == 1) bean.setCode(1);
            else bean.setCode(2);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            bean.setCode(0);
            bean.setMsg(e.getMessage());
        }

        return bean;
    }

    @PostMapping("/book")
    public @ResponseBody JsonBean addBook(@RequestParam MultipartFile imgFile, TBook book) {

        JsonBean bean = new JsonBean();

        String fileName = imgFile.getOriginalFilename();

        String path = "D:/bookimg/";
        File file = new File(path, fileName);

        try {
            imgFile.transferTo(file);
            String img = new String("load/images/") + file.getName();
            book.setImg(img);
            bookService.addBook(book);
            bean.setCode(1);

        } catch (Exception e) {
            // TODO: handle exception
            bean.setCode(0);
            bean.setMsg(e.getMessage());
        }

        return bean;
    }

    @PostMapping(value="/updateBookWithImg")
    public JsonBean updateBook(@RequestParam MultipartFile imgFile, TBook book, @CookieValue("bookId")Integer resBookId) {
        JsonBean bean = new JsonBean();

        try {
            String fileName = imgFile.getOriginalFilename();
            String path = "D:/bookimg";
            File file = new File(path, fileName);

            imgFile.transferTo(file);
            String img = "load/images/" + file.getName();
            book.setImg(img);
            bookService.updateBookWithRes(resBookId, book);
            bean.setCode(1);

        } catch (Exception e) {
            // TODO: handle exception
            bean.setCode(0);
            bean.setMsg(e.getMessage());
        }

        return bean;
    }

    @PostMapping("/updateBook")
    public JsonBean updateBook(TBook book, @CookieValue("bookId")Integer resBookId) {
        JsonBean bean = new JsonBean();

        try {

            bookService.updateBookWithRes(resBookId, book);
            bean.setCode(1);

        } catch (Exception e) {
            // TODO: handle exception
            bean.setCode(0);
            bean.setMsg(e.getMessage());
        }

        return bean;
    }

    @GetMapping("/findBookWithCookie")
    public  JsonBean findBookById(@CookieValue("bookId")Integer bookId) {
        JsonBean bean = new JsonBean();

        try {
            TBook book = bookService.findBookById(bookId);
            bean.setCode(1);
            bean.setMsg(book);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            bean.setCode(0);
            bean.setMsg(e.getMessage());
        }

        return bean;
    }
}
