package com.lana.cc.backend.service.impl;

import com.lana.cc.backend.dao.MallDao;
import com.lana.cc.backend.pojo.po.GoodsPO;
import com.lana.cc.backend.pojo.vo.common.ResultCodeEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.GoodsDetailReq;
import com.lana.cc.backend.pojo.vo.rsp.GoodsListRsp;
import com.lana.cc.backend.service.MallService;
import com.lana.cc.backend.utils.HttpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
    MallDao mallDao;

    @Override
    public ServiceResponseMessage fetchAllEnableGoodsDetails(){
        List<GoodsPO> goodsDetailList = mallDao.selectAllEnableGoodsDetails();
        if(null == goodsDetailList || goodsDetailList.isEmpty()){
            return ServiceResponseMessage.createBySuccessCodeMessage("当前没有可兑换的商品");
        }
        List<GoodsListRsp.Goods> goodsList = new ArrayList<>();
        for (GoodsPO goodsEntity : goodsDetailList) {
            GoodsListRsp.Goods goods = new GoodsListRsp.Goods();
            BeanUtils.copyProperties(goodsEntity,goods);
            goodsList.add(goods);
        }
        return ServiceResponseMessage.createBySuccessCodeMessage("获取成功",goodsList);
    }

    @Override
    public ServiceResponseMessage createNewGoods(GoodsDetailReq goodsDetailReq) {
        if(null == goodsDetailReq){
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.PARAMETER_IS_EMPTY,"参数错误");
        }
        GoodsPO goods = new GoodsPO();
        BeanUtils.copyProperties(goodsDetailReq,goods);
        goods.setCreateBy(HttpUtil.getUserUid());
        mallDao.insertNewGoodsDetail(goods);
        return ServiceResponseMessage.createBySuccessCodeMessage("新增加商品成功");
    }

    @Override
    public ServiceResponseMessage deleteGoodsByGoodsId(Integer goodsId) {
        mallDao.deleteGoodsByGoodsId(goodsId);
        return ServiceResponseMessage.createBySuccessCodeMessage("商品删除成功");
    }
}
