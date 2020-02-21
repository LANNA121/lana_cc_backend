package com.lana.cc.backend.pojo.enums;

/**
 * 身份权限的枚举
 *
 * @author LANA
 **/
public enum RoleEnum {
    //管理员
    OSS("OSS"),
    //普通用户
    USER("USER"),
    //登录权限
    LOG("LOG"),
    //所有用户
    ALL("ALL");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static RoleEnum getRole(String role) {
        if(role == null || "".equals(role)){
            return ALL;
        }
        for(RoleEnum roleEnum:values()){
            if(roleEnum.getRole().equals(role)){
                return roleEnum;
            }
        }
        return ALL;
    }
}
