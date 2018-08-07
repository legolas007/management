package com.primeton.order.service.impl;

import com.primeton.order.FeignInterface.FeignClient;
import com.primeton.order.entity.Cart;
import com.primeton.order.entity.CartItem;
import com.primeton.order.entity.TBook;
import com.primeton.order.service.CartService;
import com.primeton.order.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Usher
 * @Description:
 */
@Service
public class CartServiceImpl implements CartService {

    /*@Autowired
    private IBookDao bookDao;*/

    @Autowired
    private FeignClient feignClient;

    public List<CartItem> findCartInfo(String cartInfo) {
        List<CartItem> list = new ArrayList<>();

        String[] items = cartInfo.split(",");
        for (String item : items) {
            String[] ele = item.split(":");
            Integer bookId = Integer.valueOf(ele[0]);
            Integer num = Integer.valueOf(ele[1]);

            TBook book;
            try {
                book = feignClient.bookdao(bookId);
                //book = bookDao.findById(bookId);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw e;
            }

            CartItem cartItem = new CartItem();

            cartItem.setNum(num);
            cartItem.setBook(book);

            list.add(cartItem);
        }

        return list;
    }


    private List<CartItem> string2List(String cartInfo) {
        List<CartItem> list = new ArrayList<>();

        if (StringUtil.isNullOrEmpty(cartInfo)) {
            return list;
        }

        String[] items = cartInfo.split(",");
        for (String item : items) {
            String[] ele = item.split(":");
            Integer bookId = Integer.valueOf(ele[0]);
            Integer num = Integer.valueOf(ele[1]);

            TBook book = new TBook();
            book.setId(bookId);

            CartItem cartItem = new CartItem();

            cartItem.setNum(num);
            cartItem.setBook(book);

            list.add(cartItem);
        }

        return list;
    }

    private String list2String(List<CartItem> cartItems) {
        String cartInfo = "";

        int len = cartItems.size();
        for (int i = 0; i < len; i++) {
            if (i != 0) cartInfo += ",";
            cartInfo += cartItems.get(i).getBook().getId().toString() + ":" + cartItems.get(i).getNum();
        }


        return cartInfo;
    }


    @Override
    public String addCart(String[] bookIds, String cartInfo) {
        // TODO Auto-generated method stub

        List<CartItem> list = string2List(cartInfo);

        for (String bookId : bookIds) {
            Integer id = Integer.valueOf(bookId);

            boolean flag = false;
            for (CartItem item : list) {
                if (item.getBook().getId().equals(id)) {
                    item.setNum(item.getNum() + 1);
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                CartItem item = new CartItem();
                item.setNum(1);
                TBook book = new TBook();
                book.setId(id);
                item.setBook(book);
                list.add(item);
            }


        }

        String newCart = list2String(list);

        return newCart;
    }


    @Override
    public Cart findCart(String cartInfo) {
        // TODO Auto-generated method stub


        Cart cart = new Cart();
        Double totalPrice = 0.0;
        List<CartItem> list = new ArrayList<>();

        String[] items = cartInfo.split(",");
        for (String item : items) {
            String[] ele = item.split(":");
            Integer bookId = Integer.valueOf(ele[0]);
            Integer num = Integer.valueOf(ele[1]);

            TBook book;
            try {
                book = feignClient.bookdao(bookId);
               // book = bookDao.findById(bookId);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw e;
            }

            CartItem cartItem = new CartItem();
            cartItem.setNum(num);
            cartItem.setBook(book);
            totalPrice += cartItem.getBook().getPrice() * cartItem.getNum();
            list.add(cartItem);
        }

        cart.setCartItems(list);
        cart.setTotalPrice(totalPrice);

        return cart;
    }


    @Override
    public boolean checkCart(Cart cart) {
        // TODO Auto-generated method stub

        boolean flag = true;

        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getNum() > cartItem.getBook().getStock()) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    @Override
    public String changeCart(String cartInfo, Integer bookId, Integer bookNum) {
        // TODO Auto-generated method stub

        List<CartItem> cartItems = string2List(cartInfo);
        for (CartItem cartItem : cartItems) {
            if (cartItem.getBook().getId().equals(bookId)) {
                cartItem.setNum(bookNum);
                break;
            }
        }
        String newCartInfo = list2String(cartItems);

        return newCartInfo;
    }
}
