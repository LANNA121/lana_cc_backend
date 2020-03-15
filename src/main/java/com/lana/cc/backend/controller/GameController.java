package com.lana.cc.backend.controller;

import com.lana.cc.backend.annotation.Security;
import com.lana.cc.backend.pojo.enums.RoleEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.service.GameService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/21 09:39
 */

@RestController
@RequestMapping("/game")
public class GameController {
    @Resource
    GameService gameService;

    @Security(roles = {RoleEnum.ALL},checkToken = false)
    @GetMapping(value = "/question",produces  =  MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage fetchRandomGameProblem(){
        return gameService.fetchRandomGameProblem();
    }
}
