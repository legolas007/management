package com.primeton.user_manager.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: Usher
 * @Description:
 */
@Data
public class TUserDto {
    @Id
    @GeneratedValue
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private Integer islock;//�Ƿ�����
    private String rePassWord;
}
