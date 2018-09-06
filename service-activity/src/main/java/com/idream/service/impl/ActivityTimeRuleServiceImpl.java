package com.idream.service.impl;

import com.idream.commons.lib.dto.JSONPublicDto;
import com.idream.commons.lib.dto.activity.ActivityTimeRuleDto;
import com.idream.commons.lib.dto.activity.TimeDto;
import com.idream.commons.lib.dto.activity.TimeTypeDto;
import com.idream.commons.lib.mapper.ActivityTimeRuleMapper;
import com.idream.commons.lib.util.DateUtils;
import com.idream.service.ActivityTimeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ActivityTimeRuleServiceImpl implements ActivityTimeRuleService {

    @Autowired
    private ActivityTimeRuleMapper activityTimeRuleMapper;

    @Override
    public JSONPublicDto<Object> getActivityTimeRuleDetailByActivityId(Integer activityId) {
        //点击添加打卡的主题回显主题打卡的时间
        ActivityTimeRuleDto activityTimeRuleDto = activityTimeRuleMapper.getActivityTimeRuleDetailByActivityId(activityId);
        Integer type = activityTimeRuleDto.getType();
        String weekDay = activityTimeRuleDto.getWeekDay();
        JSONPublicDto<Object> jsonPublicDto = new JSONPublicDto<>();
        Map<Object, Object> map = new HashMap<>();
        if (type == 1) {
            List<TimeDto> timeDto = activityTimeRuleDto.getTimeDto();
            for (TimeDto timeDto2 : timeDto) {
                Date startTime2 = timeDto2.getStartTime();
                Date endTime2 = timeDto2.getEndTime();
                map.put("startTime", startTime2);
                map.put("endTime", endTime2);
                map.put("type", type);
            }
            jsonPublicDto.setResponseData(map);
        } else if (type == 2) {
            List<TimeDto> timeDtoList = activityTimeRuleDto.getTimeDto();
            TimeTypeDto timeTypeDto = new TimeTypeDto();
            List<TimeDto> list1 = new ArrayList<>();
            for (TimeDto timeDto2 : timeDtoList) {
                TimeDto timeDto = new TimeDto();
                timeDto.setStartTime(timeDto2.getStartTime());
                timeDto.setEndTime(timeDto2.getEndTime());
                list1.add(timeDto);
            }
            timeTypeDto.setTimeDto(list1);
            timeTypeDto.setType(type);
            jsonPublicDto.setResponseData(timeTypeDto);
        } else if (type == 3) {
            TimeTypeDto timeTypeDto = new TimeTypeDto();
            List<Date> listIntervalWeekDates = DateUtils.listIntervalWeekDates(new Date(), weekDay, 9);
            List<TimeDto> list1 = new ArrayList<>();
            for (Date date : listIntervalWeekDates) {
                TimeDto timeDto = new TimeDto();
                timeDto.setStartTime(date);
                timeDto.setEndTime(date);
                list1.add(timeDto);
            }
            timeTypeDto.setTimeDto(list1);
            timeTypeDto.setType(type);
            jsonPublicDto.setResponseData(timeTypeDto);
        }
        return jsonPublicDto;
    }

}
