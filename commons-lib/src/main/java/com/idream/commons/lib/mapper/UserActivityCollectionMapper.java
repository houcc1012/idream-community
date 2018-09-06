package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.user.AppUserCollectionDto;
import com.idream.commons.lib.model.UserActivityCollection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserActivityCollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserActivityCollection record);

    int insertSelective(UserActivityCollection record);

    UserActivityCollection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserActivityCollection record);

    int updateByPrimaryKey(UserActivityCollection record);

    @Select("SELECT * FROM user_activity_collection where user_id = #{userId} and activity_id = #{activityId}")
    UserActivityCollection selectByUserIdAndActivityId(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    @Delete("DELETE FROM user_activity_collection where user_id = #{userId} and activity_id = #{activityId}")
    int deleteByUserIdAndActivityId(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    List<AppUserCollectionDto> selectCollectionByUserId(@Param("userId") Integer userId);
}