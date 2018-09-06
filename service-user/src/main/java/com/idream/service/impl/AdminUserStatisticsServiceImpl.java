package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.user.AdminUserStatisticsDetailDto;
import com.idream.commons.lib.dto.user.AdminUserStatisticsDetailParams;
import com.idream.commons.lib.dto.user.AdminUserStatisticsDto;
import com.idream.commons.lib.dto.user.AdminUserStatisticsParams;
import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.UserLoginRecordMapper;
import com.idream.commons.lib.util.DateUtils;
import com.idream.service.AdminUserStatisticsService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: houcc
 * @Date: 2018/6/6
 */
@Service
public class AdminUserStatisticsServiceImpl implements AdminUserStatisticsService {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());
    @Autowired
    private UserLoginRecordMapper userLoginRecordMapper;

    @Override
    public List<AdminUserStatisticsDto> getRegisterUserByDevice(AdminUserStatisticsParams params) {
        try {
            if (params.getStartTime().length() != 8 || params.getEndTime().length() != 8) {
                throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "日期格式错误!");
            }
            Date startTime = DateUtils.format(params.getStartTime(), DateUtils.YYYYMMDD);
            Date endTime = DateUtils.format(params.getEndTime(), DateUtils.YYYYMMDD);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endTime);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            endTime = calendar.getTime();
            List<AdminUserStatisticsDto> list = null;
            if (params.getType() > 0) {
                list = userLoginRecordMapper.selectByDevice(params.getType(), startTime, endTime);
            } else {
                list = userLoginRecordMapper.selectByDate(startTime, endTime);
            }
            for (AdminUserStatisticsDto dto : list) {
                dto.setType(params.getType());
            }
            return list;
        } catch (ParseException e) {
            logger.error("日期格式不正确");
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "日期格式错误!");
        }
    }

    @Override
    public PagesDto<AdminUserStatisticsDetailDto> getRegisterUserListByDevice(AdminUserStatisticsDetailParams params) {
        if (params.getStartTime().length() != 8 || params.getEndTime().length() != 8) {
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "日期格式错误!");
        }
        PageHelper.startPage(params.getPage(), params.getRows());
        List<AdminUserStatisticsDetailDto> list = userLoginRecordMapper.selectDetailsByDateAndDevice(params);
        PageInfo info = new PageInfo(list);
        return new PagesDto<>(info.getList(), info.getTotal(), params.getPage(), params.getRows());
    }

    @Override
    public List<List<AdminUserStatisticsDto>> getYesterdayAndToday() {
        String todayStr = DateUtils.format(new Date(), DateUtils.YYYYMMDD);
        try {
            Date todayDate = DateUtils.format(todayStr, DateUtils.YYYYMMDD);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(todayDate);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date yesterday = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 2);
            todayDate = calendar.getTime();
            String yesterdayStr = DateUtils.format(yesterday, DateUtils.YYYYMMDD);
            List<AdminUserStatisticsDto> dtos = null;
            List<List<AdminUserStatisticsDto>> lists = new ArrayList<>();
            dtos = userLoginRecordMapper.selectByDevice(SystemEnum.ClientChannelEnum.IOS.getCode().intValue(), yesterday, todayDate);
            dtos = setStatisticsData(dtos, yesterdayStr, todayStr);
            Integer total = userLoginRecordMapper.selectTotalByDevice(SystemEnum.ClientChannelEnum.IOS.getCode().intValue());
            for (AdminUserStatisticsDto dto : dtos) {
                dto.setType(SystemEnum.ClientChannelEnum.IOS.getCode().intValue());
                dto.setTotal(total);
            }
            lists.add(dtos);
            dtos = userLoginRecordMapper.selectByDevice(SystemEnum.ClientChannelEnum.ANDROID.getCode().intValue(), yesterday, todayDate);
            dtos = setStatisticsData(dtos, yesterdayStr, todayStr);
            total = userLoginRecordMapper.selectTotalByDevice(SystemEnum.ClientChannelEnum.ANDROID.getCode().intValue());
            for (AdminUserStatisticsDto dto : dtos) {
                dto.setType(SystemEnum.ClientChannelEnum.ANDROID.getCode().intValue());
                dto.setTotal(total);
            }
            lists.add(dtos);
            dtos = userLoginRecordMapper.selectByDevice(SystemEnum.ClientChannelEnum.WECHAT.getCode().intValue(), yesterday, todayDate);
            dtos = setStatisticsData(dtos, yesterdayStr, todayStr);
            total = userLoginRecordMapper.selectTotalByDevice(SystemEnum.ClientChannelEnum.WECHAT.getCode().intValue());
            for (AdminUserStatisticsDto dto : dtos) {
                dto.setType(SystemEnum.ClientChannelEnum.WECHAT.getCode().intValue());
                dto.setTotal(total);
            }
            lists.add(dtos);
            return lists;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<AdminUserStatisticsDto> setStatisticsData(List<AdminUserStatisticsDto> dtos, String yesterdayStr, String todayStr) {
        if (CollectionUtils.isEmpty(dtos)) {
            dtos = new ArrayList<>();
            AdminUserStatisticsDto yesterdayStaticsDto = new AdminUserStatisticsDto();
            yesterdayStaticsDto.setDate(yesterdayStr);
            dtos.add(yesterdayStaticsDto);
            AdminUserStatisticsDto todayStaticsDto = new AdminUserStatisticsDto();
            todayStaticsDto.setDate(todayStr);
            dtos.add(todayStaticsDto);
        } else if (dtos.size() == 1) {
            AdminUserStatisticsDto staticsDto = new AdminUserStatisticsDto();
            if (dtos.get(0).getDate().equalsIgnoreCase(todayStr)) {
                staticsDto.setDate(yesterdayStr);
                dtos.add(0,staticsDto);
            } else {
                staticsDto.setDate(todayStr);
                dtos.add(staticsDto);
            }
        }
        return dtos;
    }
}
