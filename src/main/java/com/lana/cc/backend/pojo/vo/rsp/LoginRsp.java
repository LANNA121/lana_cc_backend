package com.lana.cc.backend.pojo.vo.rsp;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/21 21:30
 */
public class LoginRsp {
    private Integer uid;
    private String token;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
