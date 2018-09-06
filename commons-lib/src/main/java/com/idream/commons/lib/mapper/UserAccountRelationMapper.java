package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserAccountRelation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserAccountRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAccountRelation record);

    int insertSelective(UserAccountRelation record);

    UserAccountRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAccountRelation record);

    int updateByPrimaryKey(UserAccountRelation record);

    //根据userId查询用户账户第三方关联信息
    @Select("select * from user_account_relation where user_id = #{userId}")
    List<UserAccountRelation> selectByUserId(@Param("userId") Integer userId);

    //根据userID和账号类型查询账号信息
    @Select("select * from user_account_relation where user_id = #{userId} and account_type = #{accountType}")
    UserAccountRelation selectByUserIdAndAccountType(@Param("userId") Integer userId, @Param("accountType") Byte accountType);

    //根据userID删除用户账号
    @Delete("delete from user_account_relation where user_id = #{userId}")
    int deleteByUserId(@Param("userId") Integer userId);

    //根据账号名称和账号类型查询用户账号信息
    @Select("select * from user_account_relation where account_name = #{accountName} and account_type = #{accountType}")
    UserAccountRelation selectByAccountNameAndType(@Param("accountName") String accountName, @Param("accountType") Byte accountType);

    //根据用户ID和账号类型删除用户账号信息
    @Delete("delete from user_account_relation where user_id = #{userId} and account_type = #{accountType}")
    int deleteByUserIdAndAccountType(@Param("userId") Integer userId, @Param("accountType") Byte accountType);

    //根据账号名称查询账号信息
    @Select("select * from user_account_relation where account_name = #{accountName}")
    UserAccountRelation selectByAccountName(@Param("accountName") String accountName);

    //根据用户ID和账号类型修改账号名
    @Update("update user_account_relation set account_name = #{accountName}, update_time = now() where user_id = #{userId} and account_type = #{accountType}")
    int updateAccountNameByUserIdAndAccountType(@Param("accountName") String accountName, @Param("userId") Integer userId, @Param("accountType") Byte accountType);
}