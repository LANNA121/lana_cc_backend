package com.lana.cc.backend.dao;

import com.lana.cc.backend.pojo.po.AccountBookPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/3/22 13:23
 */

@Mapper
public interface AccountBookDao {

    /**
     * 插入新的账本兑换信息
     *
     * @param userUid 用户uid
     * @param lanaId  LanaId
     * @param point   积分标识
     * @param remark  基本备注
     */
    @Insert("insert into lana_account_book(lana_id, uid,type, count, remark, create_time, status) VALUES " +
            "(#{lanaId},#{userUid},'achieve',#{point},#{remark},UNIX_TIMESTAMP(now())*1000,0)")
    void insertAccountBookAchieve(@Param("userUid") Integer userUid, @Param("lanaId") String lanaId, @Param("point") int point, @Param("remark") String remark);

    /**
     * 插入新的账本兑换信息
     *
     * @param userUid 用户uid
     * @param lanaId  LanaId
     * @param point   积分标识
     * @param remark  基本备注
     */
    @Insert("insert into lana_account_book(lana_id, uid,type, count, remark, create_time, status) VALUES " +
            "(#{lanaId},#{userUid},'consume',#{point} * -1,#{remark},UNIX_TIMESTAMP(now())*1000,0)")
    void insertAccountBookConsume(@Param("userUid") Integer userUid, @Param("lanaId") String lanaId, @Param("point") int point, @Param("remark") String remark);


    /**
     * 查询用户的剩余积分
     *
     * @param uid 用户uid
     * @return 用户剩余积分
     */
    @Select("SELECT sum(count) as points FROM lana_account_book WHERE uid = #{uid}")
    Long selectAccountRemainingPointsByUid(@Param("uid") int uid);

    /**
     * 通过Uid查询用户说有的积分变化明细
     *
     * @param uid 用户Uid
     * @return 查询到的用户积分明细
     */
    @Select("select * from lana_account_book where uid = #{uid} order by create_time desc")
    List<AccountBookPO> selectAccountBookHistoryByUid(@Param("uid") Integer uid);
}
