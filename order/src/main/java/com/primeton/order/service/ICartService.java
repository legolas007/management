package com.primeton.order.service;

import java.util.List;

import com.primeton.order.entity.Books;

public interface ICartService {

	public String addCart(String[] bookIds, String cartId);
	
	// ��ѯ���ﳵ����ʾ������
	public List<Books> findCartInfo(String ids);
}
