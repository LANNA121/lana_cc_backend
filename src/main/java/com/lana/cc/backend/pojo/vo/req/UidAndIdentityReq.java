package com.lana.cc.backend.pojo.vo.req;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/24 22:25
 */
public class UidAndIdentityReq {
    private int uid;
    private String role;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
