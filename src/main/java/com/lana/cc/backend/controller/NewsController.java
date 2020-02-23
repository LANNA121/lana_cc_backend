package com.lana.cc.backend.controller;

import com.lana.cc.backend.annotation.Security;
import com.lana.cc.backend.pojo.enums.RoleEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.NewsDetailReq;
import com.lana.cc.backend.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/23 15:03
 */

@RestController
@RequestMapping(value = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsController {

    @Autowired
    NewsService newsService;

    @Security(roles = RoleEnum.ALL, checkToken = false)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage fetchAllNews() {
        return newsService.fetchAllNews();
    }

    @Security(roles = RoleEnum.OSS)
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage postNewsDetail(@RequestBody NewsDetailReq newsDetailReq) {
        return newsService.fetchAllNews();
    }
}
