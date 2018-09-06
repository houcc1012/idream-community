package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.model.UserActivityRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface UserActivityRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserActivityRecord record);

    int insertSelective(UserActivityRecord record);

    UserActivityRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserActivityRecord record);

    int updateByPrimaryKey(UserActivityRecord record);

    List<UserActivityRecord> getUserActivityRecordByUserId(Integer authUserId);

    //查询活动的报名人数
    @Select("SELECT count(*) FROM user_activity_record WHERE activity_id = #{activityId} AND exit_status=1")
    Integer countUserRegist(Integer activityId);

    @Select("SELECT DISTINCT user_id FROM user_activity_record WHERE activity_id = #{activityId} AND exit_status = 1")
    List<Integer> getUserIdsFromUserActivityRecordByActivityId(@Param("activityId") Integer activityId);

    FindRegisterResponseDto getRegistInfoByUserId(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    @Select("SELECT COUNT(*) FROM user_activity_record WHERE user_id=#{userId} AND exit_status=1")
    int countActivityRecordByUserId(@Param("userId") Integer userId);

    List<AppJoinActivityDto> selectJoinActivityRecordByUserId(@Param("userId") Integer userId);

    UserActivityRecord getRecordByUserIdAndActivityId(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    @Delete("DELETE FROM user_activity_record WHERE user_id = #{userId} AND activity_id = #{activityId}")
    void deleteByUserId(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    @Select("SELECT * FROM user_activity_record WHERE user_id = #{userId} AND activity_id = #{activityId}")
    UserActivityRecord getUserActivityRecord(@Param("activityId") Integer activityId, @Param("userId") Integer userId);

    @Update("UPDATE user_activity_record SET exit_status = 1 WHERE user_id = #{userId} AND activity_id = #{activityId}")
    int updateExitStatusByUserId(@Param("activityId") Integer activityId, @Param("userId") Integer userId);

    @Update("UPDATE user_activity_record SET exit_status = #{status} WHERE user_id = #{userId} AND activity_id = #{activityId}")
    void updateExitStatusQuit(@Param("activityId") int activityId, @Param("userId") int userId, @Param("status") int status);

    List<AdminActivityStatisticsDto> selectRecordByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    Integer selectTotalRecord();

    List<AdminActivitySignStatisticsDto> selectActivitySignDetail(AdminActivitySignStatisticsParams params);

    @Select("SELECT count(*) FROM user_activity_record WHERE activity_id = #{activityId}")
    Integer countActivityRecordByActivityId(@Param("activityId") Integer activityId);

    //查询当前活动允许参加的人数
    @Select("SELECT COUNT(id) FROM user_activity_record WHERE activity_id = #{activityId}")
    Integer selectActivityCount(Integer activityId);

    //查询当前活动是否参加,以及参加了多少人
    ActivityJoinedStatusAndCount getActivityJoinedAndCurrentCountPeople(@Param("authUserId") Integer authUserId, @Param("activityId") Integer activityId);

}