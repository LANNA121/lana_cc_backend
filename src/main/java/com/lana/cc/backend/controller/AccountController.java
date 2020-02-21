package com.lana.cc.backend.controller;

import com.lana.cc.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/20 17:38
 */

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;


    @GetMapping("/test")
    public String testController(){
        accountService.testConn();
        return "Hello World";
    }
}
