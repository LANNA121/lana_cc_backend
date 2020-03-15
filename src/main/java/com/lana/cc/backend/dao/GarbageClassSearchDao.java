package com.lana.cc.backend.dao;

import com.lana.cc.backend.pojo.po.GarbageCategoriesPO;
import com.lana.cc.backend.pojo.po.GarbageClassSearchPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/15 12:07
 */
@Mapper
public interface GarbageClassSearchDao {

    @Select(" select name, sortId\n" +
            "        from lana_garbage_search\n" +
            "        where name like concat('%', #{searchKey}, '%');")
    List<GarbageClassSearchPO> selectGarbageClassByLikeSearchKey(String searchKey);

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
    GarbageCategoriesPO selectGarbageCategoriesByNum(Integer classNum);
}
