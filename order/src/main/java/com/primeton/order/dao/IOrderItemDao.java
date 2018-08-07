package com.primeton.order.dao;

import com.primeton.order.base.IBaseDao;
import com.primeton.order.entity.TOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: Usher
 * @Description:
 */
@Repository
@Mapper
public interface IOrderItemDao extends IBaseDao<TOrderItem> {
    public List<TOrderItem> findByIndex(Map<String, Object> info);

}