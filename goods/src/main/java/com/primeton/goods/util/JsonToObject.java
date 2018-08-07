package com.primeton.goods.util;

import com.google.gson.Gson;
import com.primeton.goods.vo.JsonBean;

/**
 * @Author: Usher
 * @Description:
 */
public class JsonToObject {
    /**
     * json to javabean
     *
     * @param json
     */
    public static  JsonBean jsonToJavaBean(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, JsonBean.class);
        //System.out.println(person.toString());
    }
}
