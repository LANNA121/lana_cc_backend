package com.lana.cc.backend.dao;

import com.lana.cc.backend.pojo.po.NewsPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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


}
