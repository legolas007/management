package com.primeton.order.entity;

import lombok.Data;

@Data
public class TOrderItem {

    private Integer id;
    private TOrder order;
    private TBook book;
    private Integer num;

}
