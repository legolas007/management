package com.primeton.goods.vo;

/**
 * @Author: Usher
 * @Description:
 */
import lombok.Data;

import java.io.Serializable;

@Data
public class JsonBean implements Serializable {

    private static final long serialVersionUID = 7426204257089682563L;
    private Integer code;
    private Object msg;

}
