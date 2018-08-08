package com.primeton.goods.entity;

import lombok.Data;

/**
 * @Author: Usher
 * @Description:
 */
@Data
public class TBook {

    private Integer id;

    private String bookname;
    private Double price;

    private Integer stock;
    private String img;
    private Integer isDelete;
}
