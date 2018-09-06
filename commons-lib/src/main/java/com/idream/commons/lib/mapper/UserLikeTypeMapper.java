package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.appactivity.AppActivityUserLikeTypeResponseDto;
import com.idream.commons.lib.dto.appactivity.AppMyActivityTypeResponseDto;
import com.idream.commons.lib.model.UserLikeType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserLikeTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLikeType record);

    int insertSelective(UserLikeType record);

    UserLikeType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLikeType record);

    int updateByPrimaryKey(UserLikeType record);

    @Select("SELECT * FROM user_like_type WHERE user_id = #{userId} AND type_id = #{typeId}")
    UserLikeType selectByUserIdAndTypeId(@Param("typeId") Integer typeId, @Param("userId") Integer userId);

    List<AppActivityUserLikeTypeResponseDto> getInterestTypeByUserId(@Param("userId") Integer userId);

    List<AppActivityUserLikeTypeResponseDto> getOtherTypeListByUserId(@Param("userId") Integer userId);

    @Delete("DELETE FROM user_like_type WHERE user_id=#{userId}")
    void deleteLikeTypeByUserId(@Param("userId") Integer userId);

    @Select("SELECT count(*) FROM user_like_type WHERE user_id = #{userId}")
    Integer countLikeActivityTypeByUserId(@Param("userId") Integer userId);
}