package com.primeton.order.service;

import com.primeton.order.entity.Cart;
import com.primeton.order.entity.CartItem;

import java.util.List;

/**
 * @Author: Usher
 * @Description:
 */
public interface CartService {

    public String addCart(String[] bookIds, String cartInfo);

    public List<CartItem> findCartInfo(String cartInfo);

    public Cart findCart(String cartInfo);

    public boolean checkCart(Cart cart);

    public String changeCart(String cartInfo, Integer bookId, Integer bookNum);
}
