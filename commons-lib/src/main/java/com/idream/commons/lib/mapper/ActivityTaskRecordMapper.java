package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.AdminActivityStatisticsDto;
import com.idream.commons.lib.dto.activity.AdminActivityTaskStatisticsDto;
import com.idream.commons.lib.dto.activity.AdminActivityTaskStatisticsParams;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.idream.commons.lib.dto.activity.ActivityThemeDto;
import com.idream.commons.lib.model.ActivityTaskRecord;

import java.util.Date;
import java.util.List;

public interface ActivityTaskRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityTaskRecord record);

    int insertSelective(ActivityTaskRecord record);

    ActivityTaskRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityTaskRecord record);

    int updateByPrimaryKey(ActivityTaskRecord record);

    @Select("Select count(*) from activity_task_record where task_id=#{taskId} and user_id=#{userId}")
    int countByUserIdAndTaskId(@Param("userId") Integer userId, @Param("taskId") Integer taskId);

    /**
     * 查询指定日期打卡数
     */
    List<AdminActivityStatisticsDto> selectTasksByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    Integer selectTotalTasks();

    @Select("select count(*) from activity_task_record where user_id=#{userId} and activity_id=#{activityId}")
    int countByUserIdAndActivityId(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    List<AdminActivityTaskStatisticsDto> selectActivityTaskDetail(AdminActivityTaskStatisticsParams params);
}