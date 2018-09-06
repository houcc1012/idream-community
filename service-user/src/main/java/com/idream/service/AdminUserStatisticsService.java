package com.idream.service;

import com.github.pagehelper.Page;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.PagesParam;
import com.idream.commons.lib.dto.user.AdminUserStatisticsDetailDto;
import com.idream.commons.lib.dto.user.AdminUserStatisticsDetailParams;
import com.idream.commons.lib.dto.user.AdminUserStatisticsDto;
import com.idream.commons.lib.dto.user.AdminUserStatisticsParams;

import java.util.List;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
public interface AdminUserStatisticsService {
    List<AdminUserStatisticsDto> getRegisterUserByDevice(AdminUserStatisticsParams params);

    PagesDto<AdminUserStatisticsDetailDto> getRegisterUserListByDevice(AdminUserStatisticsDetailParams params);

    List<List<AdminUserStatisticsDto>> getYesterdayAndToday();
}
