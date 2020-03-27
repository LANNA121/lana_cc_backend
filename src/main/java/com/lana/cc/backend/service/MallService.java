package com.lana.cc.backend.service;

import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.GoodsDetailReq;
import com.lana.cc.backend.pojo.vo.req.HandlerBillReq;
import com.lana.cc.backend.pojo.vo.req.ModifyGoodsDetailReq;
import com.lana.cc.backend.pojo.vo.req.RedeemGiftReq;

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

    /**
     * 更新商品信息
     *
     * @param modifyGoodsDetailReq 修改商品信息的请求
     * @return 更新成功的商品明细
     */
    ServiceResponseMessage updateGoodsDetails(ModifyGoodsDetailReq modifyGoodsDetailReq);

    /**
     * 兑换商品礼品
     *
     * @param redeemGiftReq 兑换商品礼品请求
     * @return 兑换礼品兑换结果
     */
    ServiceResponseMessage redeemGiftItems(RedeemGiftReq redeemGiftReq);

    /**
     * 根据Uid查询用户账单记录
     *
     * @param uid 用户uid
     * @return 查询到的兑换记录的账单结果
     */
    ServiceResponseMessage fetchAllBillByUid(Integer uid);

    /**
     * 查询所有的账单记录
     *
     * @return 查询到的兑换记录的账单结果
     */
    ServiceResponseMessage fetchAllOssBill();

    ServiceResponseMessage handlerAccountBill(HandlerBillReq handlerBillReq);
}
