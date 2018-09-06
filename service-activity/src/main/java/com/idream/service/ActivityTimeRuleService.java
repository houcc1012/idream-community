package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicDto;

public interface ActivityTimeRuleService {

    //点击添加打卡的主题回显主题打卡的时间
    JSONPublicDto<Object> getActivityTimeRuleDetailByActivityId(Integer activityId);
}
