package com.lana.cc.backend.service.impl;

import com.lana.cc.backend.dao.AccountDao;
import com.lana.cc.backend.pojo.enums.RoleEnum;
import com.lana.cc.backend.pojo.po.AccountPO;
import com.lana.cc.backend.pojo.vo.common.ResultCodeEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.LoginReq;
import com.lana.cc.backend.pojo.vo.rsp.LoginRsp;
import com.lana.cc.backend.service.AccountService;
import com.lana.cc.backend.utils.JWTUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/21 11:53
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    AccountDao accountDao;


    @Override
    public ServiceResponseMessage login(LoginReq loginReq) {
        AccountPO accountInfo = accountDao.selectAccountInfoByUserName(loginReq.getUserName());
        if (null == accountInfo) {
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.ERROR_ACCOUNT, "账户不存在");
        }
        if (accountInfo.getPassword().equals(loginReq.getPassword())) {
            LoginRsp loginRsp = new LoginRsp();
            loginRsp.setUid(accountInfo.getUid());
            loginRsp.setToken(JWTUtil.createToken(accountInfo.getUid(),
                    RoleEnum.getRole(accountInfo.getRole()), accountInfo.getPassword()));
            return ServiceResponseMessage.createBySuccessCodeMessage("登录成功", loginRsp);
        }
        return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.USERNAME_OR_PASSWORD_ERROR,"密码错误");
    }
}
