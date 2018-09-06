package com.idream.service;

import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;

import java.util.List;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
public interface AdminActivityStatisticsService {
    List<AdminActivityStatisticsDto> getActivityData(AdminActivityStatisticsParams params);

    List<List<AdminActivityStatisticsDto>> getYesterdayAndToday();

    PagesDto<AdminActivitySignStatisticsDto> getActivitySignDetail(AdminActivitySignStatisticsParams params);

    PagesDto<AdminActivityTaskStatisticsDto> getActivityTaskDetail(AdminActivityTaskStatisticsParams params);

    PagesDto<AdminActivityInviteStatisticsDto> getActivityInviteDetail(AdminActivityInviteStatisticsParams params);

}
