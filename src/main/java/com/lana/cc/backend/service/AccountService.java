package com.lana.cc.backend.service;

import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.LoginReq;
import com.lana.cc.backend.pojo.vo.req.ModifyProfileReq;
import com.lana.cc.backend.pojo.vo.req.RegisterReq;

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
}
