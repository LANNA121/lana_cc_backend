package com.lana.cc.backend.pojo.po;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/17 23:59
 */
public class AccountAddressPO {
    /**
     * 地址ID
     */
    private int id;

    /**
     * 地址绑定人的uid
     */
    private int uid;

    /**
     * 收件人名称
     */
    private String name;

    /**
     * 收件人电话
     */
    private String phone;

    /**
     * 收件人地址
     */
    private String address;

    /**
     * 收件人房间号及地址补充
     */
    private String house;

    /**
     * 地址条数状态
     */
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
