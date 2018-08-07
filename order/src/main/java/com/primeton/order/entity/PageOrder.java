package com.primeton.order.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: Usher
 * @Description:
 */
@Data
public class PageOrder {

    private Integer id;
    private String orderNum; //�������
    private Double totalPrice;
    private Date createDate;
    private Integer state; //
    private TUser buyer; //������
    private List<TOrderItem> orderItems;
}
