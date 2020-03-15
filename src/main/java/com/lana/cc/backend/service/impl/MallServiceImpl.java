package com.lana.cc.backend.service.impl;

import com.lana.cc.backend.dao.MallDao;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.service.MallService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/26 10:01
 */

@Service
public class MallServiceImpl implements MallService {

    @Resource
    MallDao mallDao;

    @Override
    public ServiceResponseMessage fetchAllEnableGoodsDetails(){
        return null;
    }
}
