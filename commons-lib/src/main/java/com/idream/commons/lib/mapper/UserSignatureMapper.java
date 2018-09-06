package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.UserSignature;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserSignatureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSignature record);

    int insertSelective(UserSignature record);

    UserSignature selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserSignature record);

    int updateByPrimaryKey(UserSignature record);

    @Select("SELECT content FROM user_signature WHERE user_id=#{userId} ORDER BY id DESC LIMIT 1")
    String selectByUserId(@Param("userId") Integer userId);

    @Select("SELECT id,content FROM user_signature WHERE user_id=#{userId} ORDER BY id DESC LIMIT 1")
    UserSignature selectUserSignatureByUserId(@Param("userId") Integer userId);
}