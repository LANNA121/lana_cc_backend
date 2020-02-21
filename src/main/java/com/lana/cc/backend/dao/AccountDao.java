package com.lana.cc.backend.dao;

import com.lana.cc.backend.pojo.po.AccountPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/21 11:52
 */

@Mapper
public interface AccountDao {

    /**
     * 查询用户的信息 根据用户UID
     *
     * @param uid 用户UID
     * @return 查询到的用户信息
     */
    @Select("select * from lana_account where uid = #{uid}")
    AccountPO selectAccountInfoByUid(@Param("uid") Integer uid);

    /**
     * 根据用户唯一账户名登录和密码查询用户
     *
     * @param userName 用户唯一账户
     * @return 查询到的用户信息
     */
    @Select("select * from lana_account where username = #{userName}")
    AccountPO selectAccountInfoByUserNameAndPassword(@Param("userName") String userName);
}
