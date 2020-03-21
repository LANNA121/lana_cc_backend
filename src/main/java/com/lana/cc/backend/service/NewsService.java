package com.lana.cc.backend.service;

import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.NewsDetailReq;

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

    /**
     * 创建新的News信息
     *
     * @param newsDetailReq 新News的明细信息
     * @return 创建的结果
     */
    ServiceResponseMessage postNewsDetail(NewsDetailReq newsDetailReq);

    /**
     * 根据newsId删除News
     *
     * @param newsId newsId
     * @return 删除新闻信息的处理结果
     */
    ServiceResponseMessage deleteNewsDetailByNewsId(int newsId);
}
