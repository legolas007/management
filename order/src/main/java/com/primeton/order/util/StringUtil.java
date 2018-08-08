package com.primeton.order.util;

public class StringUtil {

	// �ж��Ƿ���ַ�����"" or null��
	public static boolean isNullOrEmpty(String info){
		if(info == null || info.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
}
