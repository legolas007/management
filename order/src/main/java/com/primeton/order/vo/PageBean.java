package com.primeton.order.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class PageBean<T> implements Serializable {

    private static final long serialVersionUID = 4944831276762327535L;
    private Integer currentPage;
    private Integer totalPage;
    private Integer size = 4;
    private Integer count;
    private List<T> pageInfos;

}
