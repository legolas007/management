package com.primeton.goods.dto;

import lombok.Data;

/**
 * @Author: Usher
 * @Description:
 */
@Data
public class Books {

    private Integer id;
    private String bookName;
    private Double price;
    private Integer stock;
    private String img;
    private int state;

}
