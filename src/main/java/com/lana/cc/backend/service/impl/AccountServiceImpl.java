package com.lana.cc.backend.service.impl;

import com.lana.cc.backend.dao.AccountDao;
import com.lana.cc.backend.pojo.enums.RoleEnum;
import com.lana.cc.backend.pojo.po.AccountPO;
import com.lana.cc.backend.pojo.vo.common.ResultCodeEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.LoginReq;
import com.lana.cc.backend.pojo.vo.req.ModifyProfileReq;
import com.lana.cc.backend.pojo.vo.req.RegisterReq;
import com.lana.cc.backend.pojo.vo.rsp.LoginRsp;
import com.lana.cc.backend.service.AccountService;
import com.lana.cc.backend.utils.HttpUtil;
import com.lana.cc.backend.utils.JWTUtil;
import com.lana.cc.backend.utils.ObjectUtil;
import org.springframework.beans.BeanUtils;
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
        return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.USERNAME_OR_PASSWORD_ERROR, "密码错误");
    }

    @Override
    public ServiceResponseMessage signIn(RegisterReq registerReq) {
        AccountPO accountInfo = accountDao.selectAccountInfoByUserName(registerReq.getUserName());
        if (accountInfo != null) {
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.ACCOUNT_ALWAYS_EXISTS, "账号已存在");
        } else {
            accountInfo = new AccountPO();
            BeanUtils.copyProperties(registerReq, accountInfo);
            accountInfo.setCreateTime(System.currentTimeMillis());
            if (accountDao.insertNewLanaAccount(accountInfo) == 1) {
                return ServiceResponseMessage.createBySuccessCodeMessage("注册成功", "你好! " + accountInfo.getNikeName());
            } else {
                return ServiceResponseMessage.createByFailCodeMessage("注册失败");
            }
        }
    }


    @Override
    public ServiceResponseMessage modifyProfile(ModifyProfileReq modifyProfileReq) {
        if (null == modifyProfileReq) {
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.PARAMETER_IS_EMPTY,"修改的对象为空");
        }
        if(ObjectUtil.isNotEmpty(modifyProfileReq.getAvatar())){
            accountDao.updateProfileAvatarByUid(modifyProfileReq.getAvatar(), HttpUtil.getUserUid());
        }
        if(ObjectUtil.isNotEmpty(modifyProfileReq.getNikeName())){
            accountDao.updateProfileNikeNameByUid(modifyProfileReq.getNikeName(), HttpUtil.getUserUid());
        }
        if(ObjectUtil.isNotEmpty(modifyProfileReq.getSignature())){
            accountDao.updateProfileSignatureByUid(modifyProfileReq.getSignature(), HttpUtil.getUserUid());
        }
        if(ObjectUtil.isNotEmpty(modifyProfileReq.getBirthday())){
            accountDao.updateProfileBirthdayByUid(modifyProfileReq.getBirthday(), HttpUtil.getUserUid());
        }
        return ServiceResponseMessage.createBySuccessCodeMessage("修改成功");
    }
}
