package com.lana.cc.backend.dao;

import com.lana.cc.backend.pojo.po.AccountAddressPO;
import org.apache.ibatis.annotations.*;

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

    /**
     * 创建新的地址信息
     *
     * @param addressEntity 用户地址相关信息
     */

    @Insert("insert into lana_account_addr(uid, name, phone,state,city,district,street, status) " +
            "VALUES(#{addressEntity.uid},#{addressEntity.name},#{addressEntity.phone},#{addressEntity.state},#{addressEntity.city},#{addressEntity.district},#{addressEntity.street},0) ")
    void insertNewAddress(@Param("addressEntity") AccountAddressPO addressEntity);

    /**
     * 根据用户地址信息和用户Uid删除地址相关信息
     *
     * @param addressId 用户地址信息
     * @param uid       用户uid
     */
    @Delete("delete from lana_account_addr where id=#{addressId} and uid = #{uid}")
    void deleteAddressByIdAndUid(@Param("addressId") int addressId, @Param("uid") Integer uid);

    /**
     * 查询用户地址信息 根据地址相关ID
     *
     * @param addressId 用户地址ID
     * @return 查询到的兑换结果
     */
    @Select("select * from lana_account_addr where id=#{addressId}")
    AccountAddressPO selectAccountAddressByAddressId(@Param("addressId") int addressId);
}
