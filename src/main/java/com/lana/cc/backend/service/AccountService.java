package com.lana.cc.backend.service;

import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.LoginReq;

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
}
