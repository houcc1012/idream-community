package com.idream.commons.lib.mapper;

import com.idream.commons.lib.model.CommunityLifeTimeLine;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommunityLifeTimeLineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommunityLifeTimeLine record);

    int insertSelective(CommunityLifeTimeLine record);

    CommunityLifeTimeLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunityLifeTimeLine record);

    int updateByPrimaryKey(CommunityLifeTimeLine record);

    List<CommunityLifeTimeLine> selectUserInfoByUserIdAndSearchType(@Param("userId") int userId, @Param("type") int type, @Param("activityId") int activityId);

    int insertBatch(@Param("communityLifeTimeLines") List<CommunityLifeTimeLine> communityLifeTimeLines);

    void deleteByLifeId(Integer lifeId);

    @Delete("delete from community_life_time_line where life_id in (select id from community_life where user_id = #{userId}) and user_id = #{authUserId}")
    int deleteByUserIdAndLifeId(@Param("authUserId") int authUserId, @Param("userId") int userId);
}