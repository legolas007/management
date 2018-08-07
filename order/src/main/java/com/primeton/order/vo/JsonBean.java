package com.primeton.order.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Usher
 * @Description:
 */
@Data
public class JsonBean implements Serializable {
    private static final long serialVersionUID = -3905256492921863840L;
    private Integer code;
    private Object msg;

}
