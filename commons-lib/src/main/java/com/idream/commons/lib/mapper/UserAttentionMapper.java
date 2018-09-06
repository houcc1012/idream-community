package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.AdminFansInfoDto;
import com.idream.commons.lib.dto.activity.AdminSearchFansInfoParam;
import com.idream.commons.lib.dto.activity.AppFansInfoDto;
import com.idream.commons.lib.dto.activity.AppSearchFansInfoParam;
import com.idream.commons.lib.model.UserAttention;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserAttentionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAttention record);

    int insertSelective(UserAttention record);

    UserAttention selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAttention record);

    int updateByPrimaryKey(UserAttention record);

    @Select("SELECT COUNT(*) FROM user_attention WHERE target_user_id=#{userId}")
    int countUserFansByUserId(@Param("userId") Integer userId);

    @Select("SELECT COUNT(*) FROM user_attention WHERE user_id=#{userId}")
    int countUserAttentionsByUserId(@Param("userId") Integer userId);

    @Select("SELECT ID FROM user_attention WHERE user_id=#{authUserId} and target_user_id=#{userId}")
    Integer selectIdByTwoUserId(@Param("authUserId") Integer authUserId, @Param("userId") Integer userId);

    List<AppFansInfoDto> getMyFansList(AppSearchFansInfoParam param);

    List<AppFansInfoDto> getMyNewFansList(AppSearchFansInfoParam param);

    List<AppFansInfoDto> getMyAttendList(AdminSearchFansInfoParam param);

    int getMyFansListCount(AppSearchFansInfoParam param);

    int getMyNewFansListCount(AppSearchFansInfoParam param);

    int getMyAttendListCount(AdminSearchFansInfoParam param);

    List<AdminFansInfoDto> getFansListByAttendMe(AdminSearchFansInfoParam param);

    List<AdminFansInfoDto> getFansListByAttendOther(AdminSearchFansInfoParam param);

    List<AdminFansInfoDto> getFansListByAttendEachOther(AdminSearchFansInfoParam param);

    //根据用户ID查询用户粉丝
    @Select("SELECT user_id FROM user_attention WHERE target_user_id=#{targetUserId}")
    List<Integer> selectIdByTargetUserId(Integer targetUserId);

    int getAttendMeCount(AdminSearchFansInfoParam param);

    int getAttendOtherCount(AdminSearchFansInfoParam param);

    int getAttendEachOtherCount(AdminSearchFansInfoParam param);

    //我关注的
    @Select("select target_user_id from user_attention where user_id = #{userId}")
    List<Integer> getMyAttentionOtherList(@Param("userId") Integer userId);
}