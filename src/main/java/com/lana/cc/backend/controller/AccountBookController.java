package com.lana.cc.backend.controller;

import com.lana.cc.backend.annotation.Security;
import com.lana.cc.backend.pojo.enums.RoleEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.RedeemCoinsReq;
import com.lana.cc.backend.service.AccountBookService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    AccountBookService accountBookService;

    @Security(roles = {RoleEnum.OSS,RoleEnum.USER})
    @PostMapping(value = "/redeem",produces  =  MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage redeemCoins(@RequestBody RedeemCoinsReq redeemCoinsReq){
        return accountBookService.redeemCoins(redeemCoinsReq);
    }

    @Security(roles = {RoleEnum.OSS,RoleEnum.USER})
    @GetMapping(value = "/history",produces  =  MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage fetchAccountBookHistoryByUid(@RequestParam(value = "uid",required = false) Integer uid){
        return accountBookService.fetchAccountBookHistoryByUid(uid);
    }
}
