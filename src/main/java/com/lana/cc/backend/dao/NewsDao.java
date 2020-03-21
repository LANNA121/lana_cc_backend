package com.lana.cc.backend.dao;

import com.lana.cc.backend.pojo.po.NewsPO;
import org.apache.ibatis.annotations.*;

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
}
