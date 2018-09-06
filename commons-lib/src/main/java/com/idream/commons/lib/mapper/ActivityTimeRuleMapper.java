package com.idream.commons.lib.mapper;

import com.idream.commons.lib.dto.activity.ActivityRecentOneMapperDto;
import com.idream.commons.lib.dto.activity.ActivityTimeRuleDto;
import com.idream.commons.lib.model.ActivityTimeRule;

public interface ActivityTimeRuleMapper {
    int deleteByPrimaryKey(Integer activityId);

    int insert(ActivityTimeRule record);

    int insertSelective(ActivityTimeRule record);

    ActivityTimeRule selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(ActivityTimeRule record);

    int updateByPrimaryKey(ActivityTimeRule record);

    /**
     * @param @param  activityId
     * @param @return
     *
     * @return ActivityTimeRule
     */
    ActivityTimeRule getActivityTimeRuleByActivityId(Integer activityId);

    /**
     * @param @param activityId
     *
     * @return void
     */
    void deleteActivityTimeRuleByActivityId(Integer activityId);


    //点击添加打卡的主题回显主题打卡的时间
    ActivityTimeRuleDto getActivityTimeRuleDetailByActivityId(Integer activityId);


    //查询距离当前时间最近的未开始的活动
    ActivityRecentOneMapperDto getActivityRecentOne(Integer activityId);


}