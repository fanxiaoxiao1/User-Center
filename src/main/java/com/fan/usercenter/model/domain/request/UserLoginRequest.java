package com.fan.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 *
 * @author fanxiaoxiao
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -1984205510335759021L;
    private String userAccount;
    private String userPassword;
}
