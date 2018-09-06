package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserAccount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);

    //根据用户Id修改状态
    @Update("update user_account set status = #{status}, update_time = now() where user_id = #{userId}")
    int updateAccountStatusByUserId(@Param("status") Byte status, @Param("userId") Integer userId);

    //根据账号名称查询账号信息(账号名类型必须为手机号或者普通账号)
    @Select("select a.* from user_account a, user_account_relation b where b.account_name = #{accountName} and b.account_type < 3 and a.user_id = b.user_id")
    UserAccount selectByAccountName(@Param("accountName") String accountName);

    //修改账号状态
    @Update("update user_account set status = #{status}, update_time = now() where id = #{id}")
    int updateAccountStatusById(@Param("id") Integer id, @Param("status") Byte status);

    //修改账号名称和密码
    @Update("update user_account set account_name = #{accountName},password =#{password},update_time = now() where user_id = #{userId}")
    int updateAccountInfo(@Param("accountName") String accountName, @Param("password") String password, @Param("userId") Integer userId);

    //修改账号名称和密码
    @Select("select * from user_account  where user_id = #{userId}")
    UserAccount selectByUserId(@Param("userId") Integer userId);

    //修改密码
    @Update("update user_account set password =#{password},update_time = now() where user_id = #{userId}")
    int updatePasswordByUserId(@Param("password") String password, @Param("userId") Integer userId);
}