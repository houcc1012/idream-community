package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserWyimAccount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserWyimAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserWyimAccount record);

    int insertSelective(UserWyimAccount record);

    UserWyimAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserWyimAccount record);

    int updateByPrimaryKey(UserWyimAccount record);

    @Select("select * from user_wyim_account where user_id = #{userId}")
    UserWyimAccount selectByUserId(@Param("userId") Integer userId);

    @Update("UPDATE user_wyim_account SET acc_id = #{accid},token = #{token} WHERE user_id = #{userId}")
    int updateAdminAccidAndToken(@Param("accid") String accid, @Param("token") String token, @Param("userId") Integer userId);
}