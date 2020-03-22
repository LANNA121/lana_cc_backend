package com.lana.cc.backend.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}
