package com.idream.service.impl;

import com.idream.commons.lib.dto.activity.ActivityThemeDto;
import com.idream.commons.lib.dto.activity.ActivityThemesDto;
import com.idream.commons.lib.dto.activity.TimeDto;
import com.idream.commons.lib.enums.ActivityEnum;
import com.idream.commons.lib.mapper.ActivityThemeMapper;
import com.idream.commons.lib.model.ActivityTheme;
import com.idream.service.ActivityThemeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityThemeServiceImpl implements ActivityThemeService {

    @Autowired
    private ActivityThemeMapper activityThemeMapper;

    //通过主题id获得活动主题
    @Override
    public ActivityThemeDto getActivityThemeByThemeId(Integer themeId) {
        List<ActivityThemesDto> activityThemesDtoList = activityThemeMapper.getActivityThemeByThemeId(themeId);
        TimeDto timeDto = new TimeDto();
        List<TimeDto> list = new ArrayList<>();
        ActivityThemeDto activityThemeDto = new ActivityThemeDto();
        for (ActivityThemesDto activityThemesDto2 : activityThemesDtoList) {
            Date startTime = activityThemesDto2.getStartTime();
            Date endTime = activityThemesDto2.getEndTime();
            timeDto.setStartTime(startTime);
            timeDto.setEndTime(endTime);
            list.add(timeDto);
            Integer activityId = activityThemesDto2.getActivityId();
            String content = activityThemesDto2.getContent();
            String title = activityThemesDto2.getTitle();
            Integer themeId2 = activityThemesDto2.getThemeId();
            Integer status = activityThemesDto2.getStatus();
            activityThemeDto.setThemeId(themeId2);
            activityThemeDto.setActivityId(activityId);
            List<String> contents = new ArrayList<String>();
            for (String str : StringUtils.split(content, "#@")) {
                contents.add(str);
            }
            activityThemeDto.setContent(contents);
            activityThemeDto.setTimeDto(list);
            activityThemeDto.setTitle(title);
            activityThemeDto.setStatus(status);
        }
        return activityThemeDto;
    }

    //修改主题
    @Override
    public void updateActivityThemeByThemeId(ActivityThemeDto bean, int authUserId) {
        ActivityTheme activityTheme = new ActivityTheme();
        Date date = new Date();
        Integer themeId = bean.getThemeId();
        Integer activityId = bean.getActivityId();
        String title = bean.getTitle();
        activityTheme.setId(themeId);
        activityTheme.setActivityId(activityId);
        String content = bean.getContent().stream().collect(Collectors.joining("#@"));
        activityTheme.setContent(content);
        activityTheme.setTitle(title);
        activityTheme.setType(ActivityEnum.ActivityThemeTypeEnum.USER_DEFINED.getCode());
        activityTheme.setUpdateTime(date);
        activityThemeMapper.updateActivityThemeByThemeId(activityTheme, authUserId);
    }

}
