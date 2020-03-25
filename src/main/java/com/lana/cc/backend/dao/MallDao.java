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
     * 查询当前所有可用的商品信息
     *
     * @return 查询到的商品信息
     */
    @Select("select * from lana_mall_goods order by create_time desc")
    List<GoodsPO> selectAllGoodsDetails();


    /**
     * 修稿商品Name
     *
     * @param goodsName 商品名
     * @param goodsId   商品ID
     */
    @Update("update lana_mall_goods set goods_name = #{goodsName} where id = #{goodsId}")
    void updateMallGoodsNameByGoodsId(@Param("goodsName") String goodsName, @Param("goodsId") int goodsId);

    /**
     * 修改商品描述
     *
     * @param goodsDescription 商品描述
     * @param goodsId          商品ID
     */
    @Update("update lana_mall_goods set goods_description = #{goodsDescription} where id = #{goodsId}")
    void updateMallGoodsDescriptionByGoodsId(@Param("goodsDescription") String goodsDescription, @Param("goodsId") int goodsId);

    /**
     * 修改商品图片URL修改商品信息
     *
     * @param goodsUrl 商品URL
     * @param goodsId  商品ID
     */
    @Update("update lana_mall_goods set goods_url = #{goodsUrl} where id = #{goodsId}")
    void updateMallGoodsUrlByGoodsId(@Param("goodsUrl") String goodsUrl, @Param("goodsId") int goodsId);

    /**
     * 修改商品兑换价格
     *
     * @param price   商品价格
     * @param goodsId 商品ID
     */
    @Update("update lana_mall_goods set price = #{price} where id = #{goodsId}")
    void updateMallGoodsPriceByGoodsId(@Param("price") int price, @Param("goodsId") int goodsId);

    /**
     * 修改商品数量
     *
     * @param total   商品数量
     * @param goodsId 商品ID
     */
    @Update("update lana_mall_goods set total = #{total} where id = #{goodsId}")
    void updateMallGoodsTotalByGoodsId(@Param("total") int total, @Param("goodsId") int goodsId);


    /**
     * 查询商品信息通过用户ID
     *
     * @param goodsId 商品ID
     * @return 商品信息
     */
    @Select("select * from lana_mall_goods where id = #{goodsId} limit 1")
    GoodsPO selectGoodsDetailByGoodsId(@Param("goodsId") int goodsId);


    /**
     * 兑换商品 商品数量-1
     *
     * @param id    商品ID
     * @param total 期望的商品数量
     * @return 受影响的行数
     */
    @Update("update lana_mall_goods set total = total - 1  where id = #{goodsId} and total = #{total}")
    int updateExchangeMallGoodsTotalByGoodsIdAndOldGoodsTotal(@Param("id") int id, @Param("total") int total);

    /**
     * 生成新的账单记录
     *
     * @param userUid   用户Uid
     * @param lanaId    账单ID
     * @param goodsId   商品ID
     * @param addressId 地址
     */
    @Insert("insert into lana_mall_bill(id, uid, goods_id, create_time, bill_status, address_id, status) VALUES " +
            "(#{lanaId},#{userUid},#{goodsId},UNIX_TIMESTAMP(now()) * 1000,0,#{addressId},0)")
    void insertNewMallBill(@Param("userUid") Integer userUid, @Param("lanaId") String lanaId, @Param("goodsId") int goodsId, @Param("addressId") int addressId);
}
