package com.primeton.order.util;

/**
 * @Author: Usher
 * @Description:
 */

public class StringUtil {

    public static boolean isNullOrEmpty(String info){
        if(info == null || info.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}
