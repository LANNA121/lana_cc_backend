package com.lana.cc.backend.controller;

import com.lana.cc.backend.annotation.Security;
import com.lana.cc.backend.pojo.enums.RoleEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.LoginReq;
import com.lana.cc.backend.pojo.vo.req.ModifyProfileReq;
import com.lana.cc.backend.pojo.vo.req.RegisterReq;
import com.lana.cc.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/20 17:38
 */

@RestController
@RequestMapping(value = "/account", produces  =  MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    @Autowired
    AccountService accountService;

    @Security(roles = RoleEnum.ALL,checkToken = false)
    @PostMapping(value = "/login",produces  =  MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage login(@RequestBody LoginReq loginReq){
        return accountService.login(loginReq);
    }

    @Security(roles = RoleEnum.ALL,checkToken = false)
    @PostMapping(value = "/register",produces  =  MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage signIn(@RequestBody RegisterReq registerReq){
       return accountService.signIn(registerReq);
    }

    @Security(roles = {RoleEnum.USER,RoleEnum.OSS},checkToken = false)
    @PutMapping(value = "/modify",produces  =  MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage modifyProfile(@RequestBody ModifyProfileReq modifyProfileReq){
        return accountService.modifyProfile(modifyProfileReq);
    }

    @Security(roles = {RoleEnum.USER,RoleEnum.OSS},checkToken = false)
    @GetMapping(value = "/profile",produces  =  MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage fetchProfileByUid(@RequestParam("uid") Integer uid){
        return accountService.fetchProfileByUid(uid);
    }


}
