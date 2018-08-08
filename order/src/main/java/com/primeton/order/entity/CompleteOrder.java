package com.primeton.order.entity;

import java.util.List;

public class CompleteOrder {
	
	private Order order;
	private List<OrderItem> orderItems;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	
}
