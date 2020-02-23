package com.lana.cc.backend.pojo.vo.common;

/**
 * 消息返回码的统一定义
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/7/27 11:06
 */
public enum ResultCodeEnum {


    //请求成功,伴随返回值
    SUCCESS(1000),
    //请求成功，没有返回值
    SUCCESS_NO_CONTENT(1001),
    //请求成功，重置内容
    SUCCESS_RESET_CONTENT(1002),

    //客户端请求的语法错误
    BAD_REQUEST(2000),
    //用户名或密码错误
    USERNAME_OR_PASSWORD_ERROR(2002),
    // 账号已经存在
    ACCOUNT_ALWAYS_EXISTS(2004),
    // 未登录
    NOT_LOGIN(2005),
    // 错误的密钥
    ERROR_TOKEN(2006),
    // 账户不存在
    ERROR_ACCOUNT(2007),
    //没有权限
    UNAUTHORIZED(2008),
    //权限变更
    UNAUTHORIZED_CHANGE(2009),
    //图片为空
    IMAGE_IS_EMPTY(2010),
    //参数为空
    PARAMETER_IS_EMPTY(2011),

    // 未知错误
    UNKNOWN(2100),

    NOT_FIND(404);



    private int code;
    ResultCodeEnum(int code){
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }
}
