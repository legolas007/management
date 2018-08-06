package com.primeton.goods.entity;

import lombok.Data;

/**
 * @Author: Usher
 * @Description:
 */
@Data
public class CrudItem {
    private Integer id;
    //private Order order;
    private TBook book;
    private Integer num;
}
