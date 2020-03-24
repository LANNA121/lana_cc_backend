package com.lana.cc.backend.dao;

import com.lana.cc.backend.pojo.po.GarbageCategoriesPO;
import com.lana.cc.backend.pojo.po.GarbageClassSearchPO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/15 12:07
 */
@Mapper
public interface GarbageClassSearchDao {

    /**
     * 通过Key 查询可能相关的分类信息
     *
     * @param searchKey 可能存在的Key
     * @return 查询到的结果
     */
    @Select(" select name, sortId\n" +
            "        from lana_garbage_search\n" +
            "        where name like concat('%', #{searchKey}, '%');")
    List<GarbageClassSearchPO> selectGarbageClassByLikeSearchKey(@Param("searchKey") String searchKey);

    /**
     * 通过标识查询分类详细信息
     *
     * @param classNum 分类属性信息
     * @return 查询到的结果
     */
    @Select(" select id,\n" +
            "               color,\n" +
            "               bg_color,\n" +
            "               img,\n" +
            "               name,\n" +
            "               content,\n" +
            "               description,\n" +
            "               action\n" +
            "        from lana_garbage_categories\n" +
            "        where id = #{classNum}\n" +
            "        limit 1")
    GarbageCategoriesPO selectGarbageCategoriesByNum(@Param("classNum") Integer classNum);

    /**
     * 修改垃圾分类的分类属性
     *
     * @param classKey 垃圾分类的Key
     * @param classNum 垃圾分类的属性
     */
    @Update("update lana_garbage_search set sortId = #{classNum} where name = #{classKey} limit 1")
    void updateClassCategoriesByKeyAndClassNum(@Param("classKey") String classKey, @Param("classNum") Integer classNum);

    /**
     * 增加新的垃圾分类相关信息
     *
     * @param classKey 分类的Name
     * @param classNum 分类的类别
     */
    @Insert("insert into lana_garbage_search(name, sortId) values (#{classKey},#{classNum})")
    void insertNewClassCategories(@Param("classKey") String classKey, @Param("classNum") Integer classNum);

    /**
     * 删除垃圾分类属性
     *
     * @param classKey 分类相关的Key
     */
    @Delete("delete from lana_garbage_search where name = #{classKey} limit 1")
    void deleteClassCategories(@Param("classKey") String classKey);
}
