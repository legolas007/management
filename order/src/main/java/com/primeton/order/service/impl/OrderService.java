package com.primeton.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primeton.order.dao.IOrderDao;
import com.primeton.order.dao.IOrderItemDao;
import com.primeton.order.entity.CompleteOrder;
import com.primeton.order.entity.Order;
import com.primeton.order.entity.OrderItem;
import com.primeton.order.service.IOrderService;
import com.primeton.order.vo.PageBean;

@Service
public class OrderService implements IOrderService {

	
	@Autowired
	private IOrderDao orderDao;
	
	@Autowired
	private IOrderItemDao itemDao;
	
	@Override
	public void deliverGoods(Integer orderId) {
		// TODO Auto-generated method stub
		Map<String , Object> map = new HashMap<>();
		map.put("orderId", orderId);
		map.put("newStatus", OrderState.Deliver);
		
		try {
			orderDao.updateStatus(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}

	}

	@Override
	public void confirmTakeBack(Integer orderId) {
		// TODO Auto-generated method stub
		Map<String , Object> map = new HashMap<>();
		map.put("orderId", orderId);
		map.put("newStatus", OrderState.TakedBack);
		
		try {
			orderDao.updateStatus(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

	
	@Override
	public PageBean<Order> findOrderByIndex(Integer index) {
		// TODO Auto-generated method stub
		
		PageBean<Order> bean = new PageBean<>();
		
		int size = bean.getSize();
		
		int count = 0;
		try {
			count = orderDao.count();
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
		if(index <= 1) {
			current = 1;
		}else if(index >= totalPage) {
			current = totalPage;
		}else {
			current = index;
		}
		bean.setCurrentPage(current);
		
		
		
		Map<String, Object> map = new HashMap<>();
		int origin = (current - 1) * size;
		map.put("index", origin);
		map.put("size", size);
		List<Order> list = null;
		try {
			list = orderDao.findAll(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		bean.setPageInfo(list);
		
		return bean;
		
	}

	@Override
	public PageBean<Order> findOrderByStatus(Integer page, Integer status) {
		// TODO Auto-generated method stub
		
		
		PageBean<Order> bean = new PageBean<>();
		
		int size = bean.getSize();
		
		int count = 0;
		try {
			count = orderDao.countByStatus(status);
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
		if(page <= 1) {
			current = 1;
		}else if(page >= totalPage) {
			current = totalPage;
		}else {
			current = page;
		}
		bean.setCurrentPage(current);
		
		
		
		Map<String, Object> map = new HashMap<>();
		int origin = (current - 1) * size;
		map.put("index", origin);
		map.put("size", size);
		map.put("status", status);
		List<Order> list = null;
		try {
			list = orderDao.findByStatus(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		bean.setPageInfo(list);
		
		return bean;
		
	}

	@Override
	public CompleteOrder findCompleteOrder(Integer orderId) {
		// TODO Auto-generated method stub
		
		CompleteOrder completeOrder = new CompleteOrder();
		
		try {
			Order order = orderDao.findById(orderId);
			completeOrder.setOrder(order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			List<OrderItem> orderItems = itemDao.findOrderItemByOrderId(orderId);
			completeOrder.setOrderItems(orderItems);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return completeOrder;
	}

	@Override
	public PageBean<Order> findOrderByCategory(Integer page, Integer category) {
		// TODO Auto-generated method stub
		
		if(category.equals(-1)) {
			return findOrderByIndex(page);
		}else {
			return findOrderByStatus(page, category);
		}
		
	}


}
