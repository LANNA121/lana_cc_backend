package com.lana.cc.backend.dao;

import com.lana.cc.backend.pojo.po.AccountAddressPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/18 00:04
 */

@Mapper
public interface AddressDao {

    /**
     * 根据用户uid查询用户的所有的地址信息
     *
     * @param uid 用户UID
     * @return 查询到的用户地址信息
     */
    @Select("select * from lana_account_addr where uid = #{uid}")
    List<AccountAddressPO> selectAccountAddressByUid(@Param("uid") int uid);
}
