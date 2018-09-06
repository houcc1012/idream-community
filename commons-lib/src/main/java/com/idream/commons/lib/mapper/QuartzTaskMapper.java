package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.quartz.IntervalActivityDto;
import com.idream.commons.lib.model.ActivityTask;
import com.idream.commons.lib.model.ActivityTimeRule;
import com.idream.commons.lib.model.ActivityTimeRuleDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


/**
 * @author charles.wei
 */
@Mapper
public interface QuartzTaskMapper {

    List<ActivityTask> listTodayTask();

    void updateTaskStatus(@Param("taskId") Integer id, @Param("status") Integer status);

    /**
     * @return List<Integer>
     */
    List<Integer> listTodayActivity();

    /**
     * @param activityId
     *
     * @return ActivityTimeRule
     */
    ActivityTimeRule getActivityRuleByActivityId(Integer activityId);

    /**
     * @param activityId
     *
     * @return List<ActivityTimeRuleDetail>
     */
    List<ActivityTimeRuleDetail> listNextActivityTimeDetailByActivityId(@Param("activityId") Integer activityId);

    /**
     * @param activityId 活动id
     * @param startTime  打卡开始日期
     *
     * @return int
     */
    int findTaskExistByActivityIdAndStartTime(@Param("activityId") Integer activityId, @Param("startTime") Date startTime);

    /**
     * @param task void
     */
    void addActivityTask(ActivityTask task);

    /**
     * @return List<ActivityTimeRule>
     */
    List<IntervalActivityDto> listIntervalActivity();

    /**
     * @param id
     * @param status void
     */
    void updateActivityTimeDetailStatus(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * @param activityId
     * @param date
     *
     * @return int
     */
    int checkUncreateDetail(@Param("activityId") Integer activityId, @Param("nextDate") Date date);

    /**
     * @param activityId
     *
     * @return ActivityTimeRuleDetail
     */
    ActivityTimeRuleDetail getIntervalTimeDetail(@Param("activityId") Integer activityId);

    /**
     * @param activityId
     *
     * @return Integer
     */
    Integer getDefaultThemeIdByActivityId(@Param("activityId") Integer activityId);

    List<ActivityTimeRuleDetail> listTodayTime();
}
