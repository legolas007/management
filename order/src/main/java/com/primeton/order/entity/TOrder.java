package com.primeton.order.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Usher
 * @Description:
 */
@Data
public class TOrder {

    private Integer id;
    private String orderNum; //�������
    private Double totalPrice;
    private Date createDate;
    private Integer state; //
    private TUser buyer; //������

}
