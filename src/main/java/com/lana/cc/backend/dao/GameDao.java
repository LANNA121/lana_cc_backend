package com.lana.cc.backend.dao;

import com.lana.cc.backend.pojo.po.GarbageSearchPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/15 10:31
 */
@Mapper
public interface GameDao {

    /**
     * 随机获取指定个数的分类相关信息问题
     *
     * @param limitSize 指定的限制个数
     * @return 查询到的分类查询结果
     */
    @Select("select * from lana_garbage_search order by rand() LIMIT #{limitSize}")
    List<GarbageSearchPO> selectRandomGameProblem(@Param("limitSize") int limitSize);


}
