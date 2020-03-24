package com.lana.cc.backend.service;

import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.GoodsDetailReq;

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

    /**
     * 创建新的Goods商品信息
     *
     * @param goodsDetailReq 商品详细信息
     * @return 创建的结果
     */
    ServiceResponseMessage createNewGoods(GoodsDetailReq goodsDetailReq);

    /**
     * 删除Goods通过GoodsId
     *
     * @param goodsId 用户商品ID
     * @return 删除商品的删除结果
     */
    ServiceResponseMessage deleteGoodsByGoodsId(Integer goodsId);
}
