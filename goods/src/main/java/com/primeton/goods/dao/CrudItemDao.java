package com.primeton.goods.dao;

import com.primeton.goods.base.IBaseDao;
import com.primeton.goods.entity.CrudItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Usher
 * @Description:
 */
@Mapper
@Repository
public interface CrudItemDao extends IBaseDao<CrudItem> {

    public Integer countItemByBookId(Integer bookId);

    public List<CrudItem> findOrderItemByOrderId(Integer orderId);

}
