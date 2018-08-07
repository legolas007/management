package com.primeton.order.entity;

import lombok.Data;

@Data
public class TUser {

	private Integer id;
	private String userName;
	private String password;
	private String email;
	private Integer isLock;
	private String rePassWord;
}
