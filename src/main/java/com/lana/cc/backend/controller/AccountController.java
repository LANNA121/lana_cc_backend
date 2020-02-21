package com.lana.cc.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/20 17:38
 */

@RestController
public class AccountController {


    @GetMapping("/test")
    public String testController(){
        return "Hello World";
    }
}
