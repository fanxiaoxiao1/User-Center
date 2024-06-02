package com.fan.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author fanxiaoxiao
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 5690661308089843864L;  // @Serializable 序列化

    private String userAccount;
    private String userPassword;
    private String checkPassword;


}
