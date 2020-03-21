package com.lana.cc.backend.controller;

import com.lana.cc.backend.annotation.Security;
import com.lana.cc.backend.pojo.enums.RoleEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.ModifyNewsDetailReq;
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
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage postNewsDetail(@RequestBody NewsDetailReq newsDetailReq) {
        return newsService.postNewsDetail(newsDetailReq);
    }

    @Security(roles = RoleEnum.OSS)
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage deleteNewsDetailByNewsId(@RequestParam("newsId") int newsId) {
        return newsService.deleteNewsDetailByNewsId(newsId);
    }

    @Security(roles = RoleEnum.OSS)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage modifyNewsDetailByNewsId(@RequestBody ModifyNewsDetailReq modifyNewsDetailReq) {
        return newsService.modifyNewsDetailByNewsId(modifyNewsDetailReq);
    }

}
