package com.lana.cc.backend.pojo.vo.rsp;

import com.lana.cc.backend.pojo.vo.req.UserProfileReq;

import java.util.List;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/23 15:11
 */
public class NewsDetailsRsp {

    private List<News> topNewsList;
    private List<News> newsList;

    public List<News> getTopNewsList() {
        return topNewsList;
    }

    public void setTopNewsList(List<News> topNewsList) {
        this.topNewsList = topNewsList;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public static class News{
        private int id;
        private String title;
        private String newsUrl;
        private String image;
        private int top;
        private long createTime;
        private UserProfileReq userProfileReq;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNewsUrl() {
            return newsUrl;
        }

        public void setNewsUrl(String newsUrl) {
            this.newsUrl = newsUrl;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public UserProfileReq getUserProfileReq() {
            return userProfileReq;
        }

        public void setUserProfileReq(UserProfileReq userProfileReq) {
            this.userProfileReq = userProfileReq;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
