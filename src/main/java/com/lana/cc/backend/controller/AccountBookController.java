package com.lana.cc.backend.controller;

import com.lana.cc.backend.annotation.Security;
import com.lana.cc.backend.pojo.enums.RoleEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.RedeemPointsReq;
import com.lana.cc.backend.service.GameService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/22 15:21
 */

@RestController
@RequestMapping("/book")
public class AccountBookController {
    @Resource
    GameService gameService;

    @Security(roles = {RoleEnum.OSS,RoleEnum.USER},checkToken = false)
    @PostMapping(value = "/redeem",produces  =  MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage redeemPoints(@RequestBody RedeemPointsReq redeemPointsReq){
        return gameService.redeemPoints(redeemPointsReq);
    }
}
