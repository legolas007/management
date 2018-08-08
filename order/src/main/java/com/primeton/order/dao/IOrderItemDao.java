package com.primeton.order.dao;

import java.util.List;

import com.primeton.order.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IOrderItemDao extends IBaseDao<OrderItem> {

	public Integer countItemByBookId(Integer bookId);
	
	public List<OrderItem> findOrderItemByOrderId(Integer orderId);
	
}
