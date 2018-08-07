package com.primeton.order.dao;

import com.primeton.order.entity.TOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Author: Usher
 * @Description:
 */
@Mapper
@Repository
public interface IOrderDao {

    public TOrder findById(Integer id);

    public void updateStatus(Map<String, Object> statusInfo);

    public Integer countPageOrder(Map<String, Object> info);

    public Integer countByBuyerId(Integer buyerId);

    public Integer countPageOrderByBuyerId(Map<String, Object> info);

}
