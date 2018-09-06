package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.auth.AuthUserVo;
import com.idream.commons.lib.dto.auth.QuerySimpleUser;
import com.idream.commons.lib.dto.user.AdminUserListDto;
import com.idream.commons.lib.dto.user.AdminUserListParams;
import com.idream.commons.lib.model.AdminAccount;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdminAccountMapper {
    int deleteByPrimaryKey(Integer authUserId);

    int insert(AdminAccount record);

    int insertSelective(AdminAccount record);

    AdminAccount selectByPrimaryKey(Integer authUserId);

    int updateByPrimaryKeySelective(AdminAccount record);

    int updateByPrimaryKey(AdminAccount record);

    //根据账号名查询账号信息
    @Select("select * from admin_account where account_name = #{accountName}")
    AdminAccount selectByAccountName(@Param("accountName") String accountName);

    //修改账号状态
    @Update("update admin_account set status = #{status}, update_time = now() where id = #{id}")
    int updateAccountStatusById(@Param("id") Integer id, @Param("status") Byte status);

    //根据用户Id修改状态
    @Update("update admin_account set status = #{status}, update_time = now() where user_id = #{userId}")
    int updateAccountStatusByUserId(@Param("status") Byte status, @Param("userId") Integer userId);

    //根据用户ID查询账号信息
    @Select("select * from admin_account where user_id = #{userId}")
    AdminAccount selectByUserId(@Param("userId") Integer userId);

    /**
     * 查询账户信息
     *
     * @param query
     */
    List<AuthUserVo> selectByQuerySimpleUser(QuerySimpleUser query);

    @Delete("delete from admin_account where user_id=#{userId}")
    int deleteByUserId(@Param("userId") Integer userId);
}