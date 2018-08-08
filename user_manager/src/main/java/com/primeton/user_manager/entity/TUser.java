package com.primeton.user_manager.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: Usher
 * @Description:
 */

@Data
@Entity
@Table(name = "t_user")
public class TUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer islock;//�Ƿ�����
    //private String rePassWord;

}
