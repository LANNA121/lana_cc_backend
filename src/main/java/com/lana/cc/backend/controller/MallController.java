package com.lana.cc.backend.controller;

import com.lana.cc.backend.annotation.Security;
import com.lana.cc.backend.pojo.enums.RoleEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.GoodsDetailReq;
import com.lana.cc.backend.pojo.vo.req.ModifyGoodsDetailReq;
import com.lana.cc.backend.pojo.vo.req.RedeemGiftReq;
import com.lana.cc.backend.service.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/26 09:54
 */

@RestController
@RequestMapping("/mall")
public class MallController {

    @Autowired
    MallService mallService;

    @Security(roles = RoleEnum.ALL, checkToken = false)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage fetchAllEnableGoodsDetail() {

        return mallService.fetchAllEnableGoodsDetails();
    }

    @Security(roles = RoleEnum.OSS)
    @PostMapping(value = "goods",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage createNewGoods(@RequestBody GoodsDetailReq goodsDetailReq) {
        return mallService.createNewGoods(goodsDetailReq);
    }

    @Security(roles = RoleEnum.OSS)
    @PutMapping(value = "goods",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage updateGoodsDetails(@RequestBody ModifyGoodsDetailReq modifyGoodsDetailReq) {
        return mallService.updateGoodsDetails(modifyGoodsDetailReq);
    }

    @Security(roles = RoleEnum.OSS)
    @DeleteMapping(value = "goods",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage deleteGoods(@RequestParam("goodsId") Integer goodsId) {
        return mallService.deleteGoodsByGoodsId(goodsId);
    }

    @Security(roles = {RoleEnum.OSS,RoleEnum.USER})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage redeemGiftItems(@RequestBody RedeemGiftReq redeemGiftReq) {
        return mallService.redeemGiftItems(redeemGiftReq);
    }
}
