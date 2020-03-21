package com.lana.cc.backend.dao;

import com.lana.cc.backend.pojo.po.NewsPO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/23 15:00
 */

@Mapper
public interface NewsDao {


    /**
     * 查询的标记指定的新闻列表
     *
     * @return 新闻类别信息
     */
    @Select("select * from lana_news where top = 1")
    List<NewsPO> selectAllTopNewsDetailInfo();


    /**
     * 查询的标记指定的新闻列表
     *
     * @return 新闻类别信息
     */
    @Select("select * from lana_news where top != 1")
    List<NewsPO> selectAllCommonNewsDetailInfo();


    /**
     * 插入新的新闻信息
     *
     * @param newsDetail 新闻信息明细
     */

    @Insert("insert into lana_news(title, image, news_url, top, create_time, create_by, status) " +
            "values (#{newsDetail.title},#{newsDetail.image},#{newsDetail.newsUrl},#{newsDetail.top},#{newsDetail.createTime},#{newsDetail.createBy},0)")
    void insertNewsDetail(@Param("newsDetail") NewsPO newsDetail);

    /**
     * 根据NewsId上次News明细信息
     *
     * @param newsId NewsId
     */

    @Delete("delete from lana_news where id = #{newsId}")
    void deleteNewsDetailByNewsId(@Param("newsId") int newsId);

    /**
     * 更新News的封面图
     *
     * @param newsId newsId
     * @param image  news封面图片的Id
     */
    @Update("update lana_news set image = #{image} where id = #{newsId} limit 1")
    void updateNewsImageByNewsId(@Param("newsId") int newsId, @Param("image") String image);

    /**
     * 更新News的跳转链接
     *
     * @param newsId  NewsId
     * @param newsUrl news的Url
     */
    @Update("update lana_news set news_url = #{newsUrl} where id = #{newsId} limit 1")
    void updateNewsUrlByNewsId(@Param("newsId") int newsId, @Param("newsUrl") String newsUrl);

    /**
     * 更新News Title
     *
     * @param newsId NewsId
     * @param title NewsTitle
     */
    @Update("update lana_news set title = #{title} where id = #{newsId} limit 1")
    void updateNewsTitleByNewsId(@Param("newsId") int newsId, @Param("title") String title);

    /**
     * 更新NewsTop
     *
     * @param newsId NewsId
     * @param top NewsTopID
     */

    @Update("update lana_news set top = #{top} where id = #{newsId} limit 1")
    void updateNewsTopByNewsId(int newsId, int top);
}
