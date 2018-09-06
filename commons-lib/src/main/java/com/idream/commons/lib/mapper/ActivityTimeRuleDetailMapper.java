package com.idream.commons.lib.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.idream.commons.lib.model.ActivityTimeRuleDetail;

public interface ActivityTimeRuleDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityTimeRuleDetail record);

    int insertSelective(ActivityTimeRuleDetail record);

    ActivityTimeRuleDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityTimeRuleDetail record);

    int updateByPrimaryKey(ActivityTimeRuleDetail record);

    List<Date> selectLastDates(@Param("activityId") Integer activityId);

    void deleteByActivityId(Integer activityId);

    List<ActivityTimeRuleDetail> getActivityTimeDetailByActivityId(Integer activityId);

    @Select("SELECT * FROM activity_time_rule_detail WHERE activity_id = #{activityId} ORDER BY start_time")
    List<ActivityTimeRuleDetail> getTimeDetailByActivityId(@Param("activityId") Integer activityId);
}