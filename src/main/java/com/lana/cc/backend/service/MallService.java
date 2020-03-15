package com.lana.cc.backend.service;

import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/26 10:01
 */
public interface MallService {


    /**
     * 查询所有的可用的商品明细信息
     *
     * @return 查询成功的结果返回
     */
    ServiceResponseMessage fetchAllEnableGoodsDetails();
}
