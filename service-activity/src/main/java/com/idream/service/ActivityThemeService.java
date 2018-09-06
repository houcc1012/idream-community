package com.idream.service;

import com.idream.commons.lib.dto.activity.ActivityThemeDto;

public interface ActivityThemeService {

    //修改主题数据回显
    ActivityThemeDto getActivityThemeByThemeId(Integer themeId);

    //修改主题
    void updateActivityThemeByThemeId(ActivityThemeDto bean, int authUserId);
}
