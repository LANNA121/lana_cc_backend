package com.lana.cc.backend.service;

import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.RedeemCoinsReq;
import org.springframework.stereotype.Service;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/22 15:23
 */

@Service
public interface AccountBookService {
    /**
     * 查询账户当前的剩余积分
     *
     * @param uid 用户uid
     * @return 当前用户的剩余积分
     */
    Long fetchAccountRemainingPointsByUid(int uid);


    /**
     * 获取积分的请求参数
     *
     * @param redeemCoinsReq 兑换积分请求
     * @return 兑换的请求结果
     */
    ServiceResponseMessage redeemCoins(RedeemCoinsReq redeemCoinsReq);

    /**
     * 查询用户的账单历史
     *
     * @param uid 用户uid
     * @return 查询到的账单历史请求
     */
    ServiceResponseMessage fetchAccountBookHistoryByUid(Integer uid);
}
