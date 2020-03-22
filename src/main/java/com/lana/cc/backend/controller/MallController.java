package com.lana.cc.backend.controller;

import com.lana.cc.backend.annotation.Security;
import com.lana.cc.backend.pojo.enums.RoleEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.GoodsDetailReq;
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
    public ServiceResponseMessage fetchAllGoodsGoodsDetail() {
        return mallService.fetchAllEnableGoodsDetails();
    }

    @Security(roles = RoleEnum.OSS, checkToken = false)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage createNewGoods(@RequestBody GoodsDetailReq goodsDetailReq) {
        return mallService.createNewGoods(goodsDetailReq);
    }
}
