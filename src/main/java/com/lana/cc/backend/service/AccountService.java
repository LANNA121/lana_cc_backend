package com.lana.cc.backend.service;

import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.AddressReq;
import com.lana.cc.backend.pojo.vo.req.LoginReq;
import com.lana.cc.backend.pojo.vo.req.ModifyProfileReq;
import com.lana.cc.backend.pojo.vo.req.RegisterReq;
import com.lana.cc.backend.pojo.vo.rsp.UserProfileRsp;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/21 11:53
 */
public interface AccountService {

    /**
     * 用户的登录请求
     *
     * @param loginReq 包含用户账号和用户密码
     * @return 登陆的返回结果
     */
    ServiceResponseMessage login(LoginReq loginReq);

    /**
     * 用户的在注册接口
     *
     * @param registerReq 注册需要的信息接口
     * @return 注册的登录结果
     */
    ServiceResponseMessage signIn(RegisterReq registerReq);

    /**
     * 修改用户Profile信息的结构
     *
     * @param modifyProfileReq 修改用户Profile信息
     * @return 修改用户Profile信息
     */
    ServiceResponseMessage modifyProfile(ModifyProfileReq modifyProfileReq);

    /**
     * 通过用户UID查询用户Profile信息
     *
     * @param uid 用户UID
     * @return 用户Profile的查询结果
     */
    ServiceResponseMessage fetchProfileByUid(Integer uid);


    /**
     * 通过用户UID查询用户Profile信息
     *
     * @param uid 用户UID
     * @return 用户Profile的查询结果
     */
    UserProfileRsp fetchUserProfileByUid(Integer uid);

    /**
     * 根据UIN查询用户地址信息
     *
     * @param uid 用户UIN
     * @return 用户地址信息
     */
    ServiceResponseMessage fetchAllAddressByUid(Integer uid);

    /**
     * 新增自己的邮件地址
     *
     * @param addressReq 地址请求
     * @return 新增地址的处理结果
     */
    ServiceResponseMessage createNewAddress(AddressReq addressReq);

    /**
     * 删除用户地址信息通过地址ID和用户Uid
     *
     * @param addressId 用户地址ID
     * @param uid 用户Uid
     * @return 删除地址信息的处理结果
     */
    ServiceResponseMessage deleteAddressByIdAndUid(int addressId, Integer uid);

    /**
     * 查询所有的用户信息
     *
     * @return 查询到的用户信息的查询结果
     */
    ServiceResponseMessage fetchAllProfile();
}
