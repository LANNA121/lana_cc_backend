package com.lana.cc.backend.service.impl;

import com.lana.cc.backend.dao.NewsDao;
import com.lana.cc.backend.pojo.po.NewsPO;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.NewsDetailReq;
import com.lana.cc.backend.pojo.vo.rsp.NewsDetailsRsp;
import com.lana.cc.backend.pojo.vo.rsp.UserProfileRsp;
import com.lana.cc.backend.service.AccountService;
import com.lana.cc.backend.service.NewsService;
import com.lana.cc.backend.utils.HttpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/23 15:09
 */

@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    NewsDao newsDao;
    @Resource
    AccountService accountService;

    @Override
    public ServiceResponseMessage fetchAllNews() {
        NewsDetailsRsp newsDetailsRsp = new NewsDetailsRsp();
        List<NewsPO> topNewsList = newsDao.selectAllTopNewsDetailInfo();
        List<NewsPO> commonList = newsDao.selectAllCommonNewsDetailInfo();
        List<NewsDetailsRsp.News> topNewsDetailsNewsList = new ArrayList<>();
        List<NewsDetailsRsp.News> commentNewsDetailsNewsList = new ArrayList<>();
        buildNewsDetails(topNewsList, topNewsDetailsNewsList);
        buildNewsDetails(commonList, commentNewsDetailsNewsList);
        newsDetailsRsp.setNewsList(commentNewsDetailsNewsList);
        newsDetailsRsp.setTopNewsList(topNewsDetailsNewsList);
        return ServiceResponseMessage.createBySuccessCodeMessage("拉取成功",newsDetailsRsp);
    }

    @Override
    public ServiceResponseMessage postNewsDetail(NewsDetailReq newsDetailReq) {
        NewsPO newsDetail = new NewsPO();
        BeanUtils.copyProperties(newsDetailReq,newsDetail);
        newsDetail.setCreateBy(HttpUtil.getUserUid());
        newsDetail.setCreateTime(System.currentTimeMillis());
        newsDao.insertNewsDetail(newsDetail);
        return ServiceResponseMessage.createBySuccessCodeMessage("新增成功");
    }

    @Override
    public ServiceResponseMessage deleteNewsDetailByNewsId(int newsId) {
        newsDao.deleteNewsDetailByNewsId(newsId);
        return ServiceResponseMessage.createBySuccessCodeMessage("删除成功");
    }

    private void buildNewsDetails(List<NewsPO> commonList, List<NewsDetailsRsp.News> commentNewsDetailsNewsList) {
        if(null != commonList){
            commonList.forEach(news -> {
                NewsDetailsRsp.News rspNews = new NewsDetailsRsp.News();
                BeanUtils.copyProperties(news,rspNews);
                UserProfileRsp userProfileRsp = accountService.fetchUserProfileByUid(news.getCreateBy());
                rspNews.setUserProfileRsp(userProfileRsp == null ? new UserProfileRsp() : userProfileRsp);
                commentNewsDetailsNewsList.add(rspNews);
            });
        }
    }
}
