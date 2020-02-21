package com.lana.cc.backend.service.impl;

import com.lana.cc.backend.dao.AccountDao;
import com.lana.cc.backend.service.AccountService;
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
    public void testConn() {
        System.out.println(accountDao.selectAccountInfoByUid(100000).toString());
    }
}
