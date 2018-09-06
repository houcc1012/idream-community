package com.idream.commons.lib.mapper;

import java.util.Date;
import java.util.List;

import com.idream.commons.lib.dto.activity.AdminActivityStatisticsDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.idream.commons.lib.dto.activity.FindParticipateActivityDetailResponseDto;
import com.idream.commons.lib.dto.activity.FindThemeMessageResponseDto;
import com.idream.commons.lib.model.ActivityMessage;

public interface ActivityMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityMessage record);

    int insertSelective(ActivityMessage record);

    ActivityMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityMessage record);

    int updateByPrimaryKey(ActivityMessage record);

    /*
     * 统计主题的留言数
     */
    @Select("SELECT count(1) FROM activity_message where theme_id = #{themeId}")
    Integer countMessageRecord(Integer themeId);

    @Select("SELECT id FROM activity_message WHERE theme_id = #{themeId}")
    List<Integer> getThemeMessageIds(Integer themeId);

    FindThemeMessageResponseDto getMessageById(Integer messageId);

    //查询用户参加的活动
    @Select("select count(1) from user_activity_record a WHERE a.user_id=#{userId}")
    Integer selectUserActivityRecordList(@Param("userId") Integer userId);

    /**
     * 活动留言数
     */
    List<AdminActivityStatisticsDto> selectCommentsByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

}