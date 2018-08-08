package com.primeton.order.dao;

import java.util.List;
import java.util.Map;

import com.primeton.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IOrderDao extends IBaseDao<Order> {

	public void updateStatus(Map<String, Object> statusInfo);
	
	public List<Order> findByStatus(Map<String, Object> info);

	public Integer countByStatus(Integer status);
	
	public Order findById(Integer orderId);
	
}
