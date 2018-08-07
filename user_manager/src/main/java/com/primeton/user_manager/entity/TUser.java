package com.primeton.user_manager.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: Usher
 * @Description:
 */

@Data
@Entity
@Table(name = "t_user")
public class TUser {

    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer islock;//�Ƿ�����
    //private String rePassWord;

}
