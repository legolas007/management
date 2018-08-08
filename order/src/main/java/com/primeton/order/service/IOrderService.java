package com.primeton.order.service;

import com.primeton.order.entity.CompleteOrder;
import com.primeton.order.entity.Order;
import com.primeton.order.vo.PageBean;
import com.primeton.order.entity.Order;
import com.primeton.order.vo.PageBean;

public interface IOrderService {

	public void deliverGoods(Integer orderId);
	
	public void confirmTakeBack(Integer orderId);
	
	public PageBean<Order> findOrderByIndex(Integer index);
	
	public PageBean<Order> findOrderByStatus(Integer page, Integer status);

	//������Ҷ���
	public PageBean<Order> findOrderByCategory(Integer page, Integer category);
	
	public CompleteOrder findCompleteOrder(Integer orderId);
	
}
