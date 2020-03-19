package com.lana.cc.backend.service;

import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.RedeemPointsReq;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/15 10:24
 */
public interface GameService {
    /**
     * 随机获取20个垃圾分类问题
     *
     * @return 垃圾分类问题的服务器返回值
     */
    ServiceResponseMessage fetchRandomGameProblem();

    /**
     * 获取积分的请求参数
     *
     * @param redeemPointsReq 兑换积分请求
     * @return 兑换的请求结果
     */
    ServiceResponseMessage redeemPoints(RedeemPointsReq redeemPointsReq);
}
