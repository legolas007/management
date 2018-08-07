package com.primeton.order.entity;

/**
 * @Author: Usher
 * @Description:
 */
import lombok.Data;

import java.util.List;
@Data
public class Cart {

    private Double totalPrice;

    private List<CartItem> cartItems;

}