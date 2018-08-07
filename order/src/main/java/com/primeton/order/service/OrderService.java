package com.primeton.order.service;

import com.primeton.order.entity.PageOrder;
import com.primeton.order.vo.PageBean;

/**
 * @Author: Usher
 * @Description:
 */
public interface OrderService {

    public void addOrder(String username, String cartInfo);

    public PageBean<PageOrder> findOrderByIndex(String username, Integer page);

    public void takeBack(Integer id);

    public void receive(Integer orderId);

}

