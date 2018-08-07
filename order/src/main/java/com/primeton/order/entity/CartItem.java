package com.primeton.order.entity;

import lombok.Data;

/**
 * @Author: Usher
 * @Description:
 */
@Data
public class CartItem {

    private Integer num = 1;
    private TBook book;
}

