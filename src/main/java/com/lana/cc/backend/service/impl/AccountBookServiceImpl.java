package com.lana.cc.backend.service.impl;

import com.lana.cc.backend.dao.AccountBookDao;
import com.lana.cc.backend.pojo.po.AccountBookPO;
import com.lana.cc.backend.pojo.vo.common.ResultCodeEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.RedeemCoinsReq;
import com.lana.cc.backend.pojo.vo.rsp.AccountBookHistoryRsp;
import com.lana.cc.backend.service.AccountBookService;
import com.lana.cc.backend.utils.HttpUtil;
import com.lana.cc.backend.utils.Md5Util;
import com.lana.cc.backend.utils.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/3/22 15:29
 */
@Service
public class AccountBookServiceImpl implements AccountBookService {

    @Resource
    private AccountBookDao accountBookDao;

    @Override
    public Long fetchAccountRemainingPointsByUid(int uid) {
        Long point = accountBookDao.selectAccountRemainingPointsByUid(uid);
        return point == null ? 0L : point;
    }

    @Override
    public ServiceResponseMessage redeemCoins(RedeemCoinsReq redeemCoinsReq) {
        if (null != redeemCoinsReq && redeemCoinsReq.getCheckKey().equals(
                Md5Util.encodeByMd5(
                        redeemCoinsReq.getLanaId(),
                        String.format("%s%s", redeemCoinsReq.getPoint(), redeemCoinsReq.getSource()), HttpUtil.getUserUid()))
        ) {
            // 增加兑换记录
            accountBookDao.insertAccountBookAchieve(HttpUtil.getUserUid(), redeemCoinsReq.getLanaId(), redeemCoinsReq.getPoint(), String.format("%s 兑换", redeemCoinsReq.getSource()));
            return ServiceResponseMessage.createBySuccessCodeMessage("兑换成功");
        }
        return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.BAD_REQUEST, "兑换失败");
    }

    @Override
    public ServiceResponseMessage fetchAccountBookHistoryByUid(Integer uid) {
        if (!ObjectUtil.isNotEmpty(uid)) {
            uid = HttpUtil.getUserUid();
        }
        AccountBookHistoryRsp accountBookHistoryRsp = new AccountBookHistoryRsp();
        List<AccountBookPO> accountBook = accountBookDao.selectAccountBookHistoryByUid(uid);
        if (null == accountBook || accountBook.isEmpty()) {
            return ServiceResponseMessage.createBySuccessCodeMessage("无兑换记录",accountBookHistoryRsp);
        } else {

            List<AccountBookHistoryRsp.AccountBook> accountBookList = new ArrayList<>();
            accountBook.forEach(book->{
                AccountBookHistoryRsp.AccountBook accountBookInfo =  new   AccountBookHistoryRsp.AccountBook();
                BeanUtils.copyProperties(book,accountBookInfo);
                accountBookList.add(accountBookInfo);
            });
            accountBookHistoryRsp.setAccountBooks(accountBookList);
            return ServiceResponseMessage.createBySuccessCodeMessage("拉取成功",accountBookHistoryRsp);
        }
    }
}
