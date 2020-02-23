package com.lana.cc.backend.pojo.vo.req;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/21 21:19
 */
public class LoginReq {
    private String userName;
    private String password;
    private String role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
