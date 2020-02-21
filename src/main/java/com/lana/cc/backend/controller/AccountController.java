package com.lana.cc.backend.controller;

import com.lana.cc.backend.annotation.Security;
import com.lana.cc.backend.pojo.enums.RoleEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/20 17:38
 */

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;



    @Security(roles = RoleEnum.LOG,checkToken = false)
    @GetMapping(value = "/testJson",produces  =  MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage testJsonResult(){
        accountService.testConn();
        return ServiceResponseMessage.createBySuccessCodeMessage("Hello　Json", Arrays.asList("Hello Data"));
    }


    @Security(roles = RoleEnum.USER)
    @GetMapping("/test")
    public ServiceResponseMessage testController(){

        return ServiceResponseMessage.createBySuccessCodeMessage("Hello　Json", Arrays.asList("Hello Data"));
    }
}
