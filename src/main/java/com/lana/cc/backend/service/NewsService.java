package com.lana.cc.backend.service;

import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/23 15:08
 */
public interface NewsService {

    /**
     * 查询所有的新闻信息
     *
     * @return 查询到的新闻信息的组包信息
     */
    ServiceResponseMessage fetchAllNews();
}
