package com.example.windserver.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@TableName(value = "employee")
@Data
public class Employee implements Serializable {
    private Long id;

    private String name;

    private String username;

    private String password;


    private String phone;

    private String sex;

    private String idNumber;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Long createUser;

    private Long updateUser;
    private String salt;

    @Serial
    private static final long serialVersionUID = 1L;
}