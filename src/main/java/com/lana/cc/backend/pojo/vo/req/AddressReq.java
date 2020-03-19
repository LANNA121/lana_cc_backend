package com.lana.cc.backend.pojo.vo.req;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/19 22:07
 */
public class AddressReq {
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
}
