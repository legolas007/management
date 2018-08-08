package com.primeton.order.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.primeton.order.entity.CompleteOrder;
import com.primeton.order.entity.Order;
import com.primeton.order.service.IOrderService;
import com.primeton.order.vo.JsonBean;
import com.primeton.order.vo.PageBean;

@RestController
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@GetMapping("/test")
	public String test() {
		return "sdsd";
	}
	
	@RequestMapping(value="/deliverGoods/{orderId}", method=RequestMethod.PUT)
	public @ResponseBody JsonBean deliverGoods(@PathVariable("orderId")Integer orderId){
		JsonBean bean = new JsonBean();
		
		try {
			orderService.deliverGoods(orderId);
			bean.setCode(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
		
	}
	
	@RequestMapping(value="/confirmTakeBack/{orderId}", method=RequestMethod.PUT)
	public @ResponseBody JsonBean confirmTakeBack(@PathVariable("orderId")Integer orderId){
		JsonBean bean = new JsonBean();
		
		try {
			orderService.confirmTakeBack(orderId);
			bean.setCode(1);
			bean.setMsg(orderId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
		
	}
	
	@RequestMapping(value="/order/{page}", method=RequestMethod.GET)
	public @ResponseBody JsonBean findOrderByIndex(@PathVariable("page")Integer page) {
		JsonBean bean = new JsonBean();
		
		try {
			PageBean<Order> pageBean = orderService.findOrderByIndex(page);
			bean.setCode(1);
			bean.setMsg(pageBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}
	
	
	
	
	@RequestMapping(value="/orderByCategory/{category}/{page}", method=RequestMethod.GET)
	public @ResponseBody JsonBean findOrderByCategory(@PathVariable("page")Integer page, @PathVariable("category") Integer category) {
		JsonBean bean = new JsonBean();
		
		try {
			PageBean<Order> pageBean = orderService.findOrderByCategory(page, category);
			bean.setCode(1);
			bean.setMsg(pageBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}

	@RequestMapping(value="/completeOrder/{orderId}", method=RequestMethod.PUT)
	public @ResponseBody JsonBean findCompleteOrder(@PathVariable("orderId")Integer orderId, HttpServletResponse response) {
		JsonBean bean = new JsonBean();
		
		try {
			Cookie cookie = new Cookie("orderId", orderId.toString());
			cookie.setMaxAge(30 * 24 * 3600);
			response.addCookie(cookie);
			bean.setCode(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}

	@RequestMapping(value="/completeOrder", method=RequestMethod.GET)
	public @ResponseBody JsonBean findCompleteOrder(@CookieValue("orderId")Integer orderId) {
		JsonBean bean = new JsonBean();
		
		try {
			CompleteOrder completeOrder = orderService.findCompleteOrder(orderId);
			bean.setCode(1);
			bean.setMsg(completeOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setCode(0);
			bean.setMsg(e.getMessage());
		}
		
		return bean;
	}
	
	
	
	
}
