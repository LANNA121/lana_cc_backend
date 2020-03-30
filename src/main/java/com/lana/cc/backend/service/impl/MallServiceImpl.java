package com.lana.cc.backend.service.impl;

import java.util.UUID;

import com.lana.cc.backend.dao.AccountBookDao;
import com.lana.cc.backend.dao.AccountDao;
import com.lana.cc.backend.dao.AddressDao;
import com.lana.cc.backend.dao.MallDao;
import com.lana.cc.backend.pojo.enums.RoleEnum;
import com.lana.cc.backend.pojo.po.AccountAddressPO;
import com.lana.cc.backend.pojo.po.AccountPO;
import com.lana.cc.backend.pojo.po.GoodsPO;
import com.lana.cc.backend.pojo.po.MallBillPO;
import com.lana.cc.backend.pojo.vo.common.ResultCodeEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.GoodsDetailReq;
import com.lana.cc.backend.pojo.vo.req.HandlerBillReq;
import com.lana.cc.backend.pojo.vo.req.ModifyGoodsDetailReq;
import com.lana.cc.backend.pojo.vo.req.RedeemGiftReq;
import com.lana.cc.backend.pojo.vo.rsp.GoodsListRsp;
import com.lana.cc.backend.pojo.vo.rsp.MallAllBillRsp;
import com.lana.cc.backend.service.MallService;
import com.lana.cc.backend.utils.HttpUtil;
import com.lana.cc.backend.utils.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/26 10:01
 */

@Service
public class MallServiceImpl implements MallService {

    @Resource
    AccountDao accountDao;
    @Resource
    MallDao mallDao;
    @Resource
    AddressDao addressDao;
    @Resource
    AccountBookDao accountBookDao;

    private static final int START = 1;
    private static final int SEND = 2;
    private static final int END = 3;

    @Override
    public ServiceResponseMessage fetchAllEnableGoodsDetails() {
        List<GoodsPO> goodsDetailList;
        if (HttpUtil.getRole() == RoleEnum.OSS) {
            goodsDetailList = mallDao.selectAllGoodsDetails();
        } else {
            goodsDetailList = mallDao.selectAllEnableGoodsDetails();
        }
        if (null == goodsDetailList || goodsDetailList.isEmpty()) {
            return ServiceResponseMessage.createBySuccessCodeMessage("当前没有可兑换的商品");
        }
        List<GoodsListRsp.Goods> goodsList = new ArrayList<>();
        for (GoodsPO goodsEntity : goodsDetailList) {
            GoodsListRsp.Goods goods = new GoodsListRsp.Goods();
            BeanUtils.copyProperties(goodsEntity, goods);
            goodsList.add(goods);
        }
        return ServiceResponseMessage.createBySuccessCodeMessage("获取成功", goodsList);
    }

    @Override
    public ServiceResponseMessage createNewGoods(GoodsDetailReq goodsDetailReq) {
        if (null == goodsDetailReq) {
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.PARAMETER_IS_EMPTY, "参数错误");
        }
        GoodsPO goods = new GoodsPO();
        BeanUtils.copyProperties(goodsDetailReq, goods);
        goods.setCreateBy(HttpUtil.getUserUid());
        mallDao.insertNewGoodsDetail(goods);
        return ServiceResponseMessage.createBySuccessCodeMessage("新增加商品成功");
    }

    @Override
    public ServiceResponseMessage updateGoodsDetails(ModifyGoodsDetailReq modifyGoodsDetailReq) {
        if (null == modifyGoodsDetailReq) {
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.PARAMETER_IS_EMPTY, "参数错误");
        }
        if (ObjectUtil.isNotEmpty(modifyGoodsDetailReq.getGoodsName())) {
            mallDao.updateMallGoodsNameByGoodsId(modifyGoodsDetailReq.getGoodsName(), modifyGoodsDetailReq.getGoodsId());
        }
        if (ObjectUtil.isNotEmpty(modifyGoodsDetailReq.getGoodsDescription())) {
            mallDao.updateMallGoodsDescriptionByGoodsId(modifyGoodsDetailReq.getGoodsDescription(), modifyGoodsDetailReq.getGoodsId());
        }
        if (ObjectUtil.isNotEmpty(modifyGoodsDetailReq.getGoodsUrl())) {
            mallDao.updateMallGoodsUrlByGoodsId(modifyGoodsDetailReq.getGoodsUrl(), modifyGoodsDetailReq.getGoodsId());
        }
        if (ObjectUtil.isNotEmpty(modifyGoodsDetailReq.getPrice())) {
            mallDao.updateMallGoodsPriceByGoodsId(modifyGoodsDetailReq.getPrice(), modifyGoodsDetailReq.getGoodsId());
        }
        if (ObjectUtil.isNotEmpty(modifyGoodsDetailReq.getTotal())) {
            synchronized (ModifyGoodsDetailReq.class) {
                mallDao.updateMallGoodsTotalByGoodsId(modifyGoodsDetailReq.getTotal(), modifyGoodsDetailReq.getGoodsId());
            }
        }
        return null;
    }

    @Override
    public ServiceResponseMessage deleteGoodsByGoodsId(Integer goodsId) {
        mallDao.updateMallGoodsTotalByGoodsId(-1, goodsId);
        return ServiceResponseMessage.createBySuccessCodeMessage("商品删除成功");
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ServiceResponseMessage redeemGiftItems(RedeemGiftReq redeemGiftReq) {
        if (null == redeemGiftReq) {
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.PARAMETER_IS_EMPTY, "参数错误");
        }
        GoodsPO goodsInfo = mallDao.selectGoodsDetailByGoodsId(redeemGiftReq.getGoodsId());
        if (null == goodsInfo || goodsInfo.getTotal() <= 0) {
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.GOODS_NO_FIND_OR_DISABLE, "商品不存在或兑换结束");
        }
        AccountAddressPO address = addressDao.selectAccountAddressByAddressId(redeemGiftReq.getAddressId());
        if (null == address) {
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.ADDR_NO_FIND_OR_DISABLE, "用户地址不存在");
        }
        if (accountBookDao.selectAccountRemainingPointsByUid(HttpUtil.getUserUid()) >= goodsInfo.getPrice()) {
            int error = mallDao.updateExchangeMallGoodsTotalByGoodsIdAndOldGoodsTotal(goodsInfo.getId(), goodsInfo.getTotal());
            if (error == 1) {
                accountBookDao.insertAccountBookConsume(HttpUtil.getUserUid(), redeemGiftReq.getLanaId(), goodsInfo.getPrice(), String.format("换购商品 [%s]", goodsInfo.getGoodsName()));
                mallDao.insertNewMallBill(HttpUtil.getUserUid(), redeemGiftReq.getLanaId(), goodsInfo.getId(), address.getId());
                return ServiceResponseMessage.createBySuccessCodeMessage("兑换成功");
            }
        }
        return ServiceResponseMessage.createByFailCodeMessage("兑换失败");
    }

    @Override
    public ServiceResponseMessage fetchAllBillByUid(Integer uid) {
        if (!ObjectUtil.isNotEmpty(uid)) {
            uid = HttpUtil.getUserUid();
        }
        List<MallBillPO> mallBillList = mallDao.selectAllBillDetailsByUid(uid);
        return buildMallBillResponseMessage(mallBillList);
    }

    @Override
    public ServiceResponseMessage fetchAllOssBill() {
        List<MallBillPO> mallBillList = mallDao.selectAllBillDetails();
        return buildMallBillResponseMessage(mallBillList);
    }

    @Override
    public ServiceResponseMessage handlerAccountBill(HandlerBillReq handlerBillReq) {
        if(null == handlerBillReq){
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.PARAMETER_IS_EMPTY, "参数错误");
        }
        if(handlerBillReq.getBillStatus() != END){
            if(HttpUtil.getRole() == RoleEnum.USER ){
                return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.UNAUTHORIZED_CHANGE, "不允许的操作");
            }
        }
        if(handlerBillReq.getBillStatus() == SEND && !ObjectUtil.isNotEmpty(handlerBillReq.getTrackId())){
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.PARAMETER_IS_EMPTY, "需要单号");
        }
        if(handlerBillReq.getBillStatus() == START){
            mallDao.updateBillStatusStartAndOperatorByBillId(handlerBillReq.getBillId(),HttpUtil.getUserUid());
        } else if(handlerBillReq.getBillStatus() == SEND && ObjectUtil.isNotEmpty(handlerBillReq.getTrackId())){
            mallDao.updateBillStatusAndTrackIdByBillId(handlerBillReq.getBillId(),handlerBillReq.getTrackId());
        }else {
            mallDao.updateBillStatusByBillId(handlerBillReq.getBillId(),handlerBillReq.getBillStatus());
        }
        return ServiceResponseMessage.createBySuccessCodeMessage("处理成功");
    }

    private ServiceResponseMessage buildMallBillResponseMessage(List<MallBillPO> mallBillList) {
        MallAllBillRsp mallAllBillRsp = new MallAllBillRsp();
        if (null != mallBillList) {
            List<MallAllBillRsp.Bill> billList = new ArrayList<>();
            mallBillList.forEach(mallBill -> {
                MallAllBillRsp.Bill bill = new  MallAllBillRsp.Bill();
                BeanUtils.copyProperties(mallBill,bill);
                GoodsPO goodsPO = mallDao.selectGoodsDetailByGoodsId(mallBill.getGoodsId());
                MallAllBillRsp.Goods goods = new MallAllBillRsp.Goods();
                BeanUtils.copyProperties(goodsPO,goods);
                bill.setGoodsDetail(goods);
                MallAllBillRsp.Address address = new  MallAllBillRsp.Address();
                AccountAddressPO accountAddress = addressDao.selectAccountAddressByAddressId(mallBill.getAddressId());
                BeanUtils.copyProperties(accountAddress,address);
                bill.setAddressDetail(address);
                AccountPO accountInfo = accountDao.selectAccountInfoByUid(mallBill.getOperator());
                MallAllBillRsp.User user = new MallAllBillRsp.User();
                if(null != accountInfo){
                    BeanUtils.copyProperties(accountInfo,user);
                }
                bill.setOperatorInfo(user);
                billList.add(bill);
            });
            mallAllBillRsp.setBills(billList);
        }
        return ServiceResponseMessage.createBySuccessCodeMessage("查询成功", mallAllBillRsp);
    }
}
