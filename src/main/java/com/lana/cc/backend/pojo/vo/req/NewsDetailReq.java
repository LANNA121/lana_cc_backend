package com.lana.cc.backend.pojo.vo.req;

/**
 * @author LANA
 *
 * @version 1.0
 * @date 2020/2/23 15:43
 */
public class NewsDetailReq {
    private String title;
    private String newsUrl;
    private String image;
    private int top;


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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }
}
