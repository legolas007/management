package com.primeton.goods.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
