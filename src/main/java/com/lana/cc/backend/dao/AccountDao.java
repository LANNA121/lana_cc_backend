package com.lana.cc.backend.dao;

import com.lana.cc.backend.pojo.po.AccountPO;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
    @Select("select * from lana_account where user_name = #{userName} limit 1")
    AccountPO selectAccountInfoByUserName(@Param("userName") String userName);

    /**
     * 插入新的accountInfo
     *
     * @param accountInfo 用户Account Info
     * @return 受影响的行数
     */
    @Insert("insert into " +
            "lana_account " +
            "(role,user_name,password," +
            " nike_name,gender,avatar," +
            " create_time,update_time) " +
            "values" +
            "(" +
            "#{accountInfo.role},#{accountInfo.userName},#{accountInfo.password}," +
            "#{accountInfo.nikeName},#{accountInfo.gender},#{accountInfo.avatar}," +
            "#{accountInfo.createTime},#{accountInfo.createTime}" +
            ")")
    int insertNewLanaAccount(@Param("accountInfo") AccountPO accountInfo);

    /**
     * 更新用户头像
     *
     * @param avatar 用户头像
     * @param uid    用户UID
     */

    @Update("update lana_account set avatar = #{avatar} where uid = #{uid}")
    void updateProfileAvatarByUid(@Param("avatar") String avatar, @Param("uid") Integer uid);

    /**
     * 修改NikeName名字
     *
     * @param nikeName 用户昵称
     * @param uid      用户uid
     */
    @Update("update lana_account set nike_name = #{nikeName} where uid = #{uid}")
    void updateProfileNikeNameByUid(@Param("nikeName") String nikeName, @Param("uid") Integer uid);


    /**
     * 根据用户uid修改用户签名
     *
     * @param signature 用户个性签名
     * @param uid       用户UID
     */
    @Update("update lana_account set `signature` = #{signature} where uid = #{uid}")
    void updateProfileSignatureByUid(@Param("signature") String signature, @Param("uid") Integer uid);

    /**
     * 根据用户uid修改用户生日
     *
     * @param birthday 用户生日的时间戳
     * @param uid      用户UID
     */
    @Update("update lana_account set birthday = #{birthday} where uid = #{uid}")
    void updateProfileBirthdayByUid(@Param("birthday") Long birthday, @Param("uid") Integer uid);

    /**
     * 查询说有的Account信息
     *
     * @return 查询到的Account信息结果
     */
    @Select("select * from lana_account")
    List<AccountPO> selectAllAccountInfo();

    /**
     * 通过用户Uid更新用户身份权限
     *
     * @param uid  用户Uid
     * @param role 用户身份权限
     */
    @Update("update lana_account set role = #{role} where uid = #{uid}")
    void updateProfileRoleByAccountUid(@Param("uid") int uid, @Param("role") String role);

    /**
     * 删除用户账号信息
     *
     * @param uid 用户uid
     */
    @Delete("delete from lana_account where uid = #{uid} limit 1")
    void deleteAccountProfileByUid(@Param("uid") Integer uid);
}
