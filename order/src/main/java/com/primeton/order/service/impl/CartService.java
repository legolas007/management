package com.primeton.order.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primeton.order.dao.IBookDao;
import com.primeton.order.entity.Books;
import com.primeton.order.service.ICartService;
import com.primeton.order.util.StringUtil;

@Service
public class CartService implements ICartService{

	@Autowired
	private IBookDao bookDao;
	
	@Override
	public String addCart(String[] bookIds, String cartId) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		if(!StringUtil.isNullOrEmpty(cartId)){
			String[] split = cartId.split(",");
			list.addAll(Arrays.asList(split));
			
		}
		if(bookIds == null || bookIds.length == 0){
			throw new RuntimeException("商品编号不能为空");
		}
		// 1,2,3
		String info = "";
		for(int i = 0; i < bookIds.length; i++){
			if(!list.contains(bookIds[i])){
				list.add(bookIds[i]);
			}
		}
		
		for(String v : list){
			info += v + ",";
		}
		info = info.substring(0, info.length() - 1);
		
		return info;
	}

	@Override
	public List<Books> findCartInfo(String ids) {
		// TODO Auto-generated method stub
		
		if(StringUtil.isNullOrEmpty(ids)){
			throw new RuntimeException("编号不能为空");
		}
		try {
			String[] split = ids.split(",");
			List<String> list = new ArrayList<>(Arrays.asList(split));
			return bookDao.findByIds(split);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}

}
