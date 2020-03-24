package com.lana.cc.backend.dao;

import com.lana.cc.backend.pojo.po.GoodsPO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/26 10:24
 */

@Mapper
public interface MallDao {


    /**
     * 增加新的商品信息
     *
     * @param goods 新的商品信息
     */
    @Insert("insert into lana_mall_goods(goods_name, total, price, goods_url, goods_description, create_by, create_time, status) " +
            "VALUES (#{goods.goodsName},#{goods.total},#{goods.price},#{goods.goodsUrl},#{goods.goodsDescription},#{goods.createBy},UNIX_TIMESTAMP(now()) * 1000,0)")
    void insertNewGoodsDetail(@Param("goods") GoodsPO goods);

    /**
     * 查询当前所有可用的商品信息
     *
     * @return 查询到的商品信息
     */
    @Select("select * from lana_mall_goods where total > 0")
    List<GoodsPO> selectAllEnableGoodsDetails();

    /**
     * 通过商品ID删除商品
     *
     * @param goodsId 商品ID
     */
    @Delete("delete from lana_mall_goods where id = #{goodsId} limit 1")
    void deleteGoodsByGoodsId(@Param("goodsId") Integer goodsId);
}
