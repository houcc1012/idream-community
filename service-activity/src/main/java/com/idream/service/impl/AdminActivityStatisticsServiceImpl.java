package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.activity.*;
import com.idream.commons.lib.enums.ActivityStatisticsEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.util.DateUtils;
import com.idream.service.AdminActivityStatisticsService;
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
public class AdminActivityStatisticsServiceImpl implements AdminActivityStatisticsService {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private ActivityTaskRecordMapper activityTaskRecordMapper;
    @Autowired
    private UserActivityRecordMapper userActivityRecordMapper;
    @Autowired
    private ActivityInvitationRecordMapper activityInvitationRecordMapper;
    @Autowired
    private CommunityLifeMapper communityLifeMapper;
    @Autowired
    private UserVisitRecordMapper userVisitRecordMapper;

    @Override
    public List<AdminActivityStatisticsDto> getActivityData(AdminActivityStatisticsParams params) {
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
            List<AdminActivityStatisticsDto> dtos = null;
            if (params.getType() == ActivityStatisticsEnum.BROWSE.getCode()) {
                dtos = userVisitRecordMapper.selectViewsByDate(startTime, endTime);
            } else if (params.getType() == ActivityStatisticsEnum.SIGN.getCode()) {
                dtos = userActivityRecordMapper.selectRecordByDate(startTime, endTime);
            } else if (params.getType() == ActivityStatisticsEnum.TASK.getCode()) {
                dtos = activityTaskRecordMapper.selectTasksByDate(startTime, endTime);
            } else if (params.getType() == ActivityStatisticsEnum.INVITE.getCode()) {
                dtos = activityInvitationRecordMapper.selectInvitesByDate(startTime, endTime);
            } else if (params.getType() == ActivityStatisticsEnum.COMMENT.getCode()) {
                dtos = communityLifeMapper.selectCommentsByDate(startTime, endTime);
            }
            for (AdminActivityStatisticsDto dto : dtos) {
                dto.setType(params.getType());
            }
            return dtos;
        } catch (ParseException e) {
            logger.error("日期格式不正确");
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "日期格式错误!");
        }
    }

    @Override
    public List<List<AdminActivityStatisticsDto>> getYesterdayAndToday() {
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
            List<AdminActivityStatisticsDto> dtos = null;
            List<List<AdminActivityStatisticsDto>> lists = new ArrayList<>();
            dtos = userVisitRecordMapper.selectViewsByDate(yesterday, todayDate);
            dtos = setStatisticsData(dtos, yesterdayStr, todayStr);
            Integer total = userVisitRecordMapper.selectTotalViews();
            for (AdminActivityStatisticsDto dto : dtos) {
                dto.setType(ActivityStatisticsEnum.BROWSE.getCode());
                dto.setTotal(total);
            }
            lists.add(dtos);
            dtos = userActivityRecordMapper.selectRecordByDate(yesterday, todayDate);
            dtos = setStatisticsData(dtos, yesterdayStr, todayStr);
            total = userActivityRecordMapper.selectTotalRecord();
            for (AdminActivityStatisticsDto dto : dtos) {
                dto.setType(ActivityStatisticsEnum.SIGN.getCode());
                dto.setTotal(total);
            }
            lists.add(dtos);
            dtos = activityTaskRecordMapper.selectTasksByDate(yesterday, todayDate);
            dtos = setStatisticsData(dtos, yesterdayStr, todayStr);
            total = activityTaskRecordMapper.selectTotalTasks();
            for (AdminActivityStatisticsDto dto : dtos) {
                dto.setType(ActivityStatisticsEnum.TASK.getCode());
                dto.setTotal(total);
            }
            lists.add(dtos);
            dtos = activityInvitationRecordMapper.selectInvitesByDate(yesterday, todayDate);
            dtos = setStatisticsData(dtos, yesterdayStr, todayStr);
            total = activityInvitationRecordMapper.selectTotalInvites();
            for (AdminActivityStatisticsDto dto : dtos) {
                dto.setType(ActivityStatisticsEnum.INVITE.getCode());
                dto.setTotal(total);
            }
            lists.add(dtos);
            dtos = communityLifeMapper.selectCommentsByDate(yesterday, todayDate);
            dtos = setStatisticsData(dtos, yesterdayStr, todayStr);
            total = communityLifeMapper.selectTotalComments();
            for (AdminActivityStatisticsDto dto : dtos) {
                dto.setType(ActivityStatisticsEnum.COMMENT.getCode());
                dto.setTotal(total);
            }
            lists.add(dtos);
            return lists;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public PagesDto<AdminActivitySignStatisticsDto> getActivitySignDetail(AdminActivitySignStatisticsParams params) {
        if (params.getStartTime().length() != 8 || params.getEndTime().length() != 8) {
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "日期格式错误!");
        }
        PageHelper.startPage(params.getPage(),params.getRows());
        List<AdminActivitySignStatisticsDto> list = userActivityRecordMapper.selectActivitySignDetail(params);
        PageInfo info = new PageInfo(list);
        return new PagesDto<>(info.getList(), info.getTotal(), params.getPage(),params.getRows());
    }

    @Override
    public PagesDto<AdminActivityTaskStatisticsDto> getActivityTaskDetail(AdminActivityTaskStatisticsParams params) {
        if (params.getStartTime().length() != 8 || params.getEndTime().length() != 8) {
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "日期格式错误!");
        }
        PageHelper.startPage(params.getPage(),params.getRows());
        List<AdminActivityTaskStatisticsDto> list = activityTaskRecordMapper.selectActivityTaskDetail(params);
        PageInfo info = new PageInfo(list);
        return new PagesDto<>(info.getList(), info.getTotal(), params.getPage(),params.getRows());
    }

    @Override
    public PagesDto<AdminActivityInviteStatisticsDto> getActivityInviteDetail(AdminActivityInviteStatisticsParams params) {
        if (params.getStartTime().length() != 8 || params.getEndTime().length() != 8) {
            throw new BusinessException(RetCodeConstants.PARAMS_ERROR, "日期格式错误!");
        }
        PageHelper.startPage(params.getPage(),params.getRows());
        List<AdminActivityInviteStatisticsDto> list = activityInvitationRecordMapper.selectActivityInviteDetail(params);
        PageInfo info = new PageInfo(list);
        return new PagesDto<>(info.getList(), info.getTotal(), params.getPage(),params.getRows());
    }

    private List<AdminActivityStatisticsDto> setStatisticsData(List<AdminActivityStatisticsDto> dtos, String yesterdayStr, String todayStr) {
        if (CollectionUtils.isEmpty(dtos)) {
            dtos = new ArrayList<>();
            AdminActivityStatisticsDto yesterdayStaticsDto = new AdminActivityStatisticsDto();
            yesterdayStaticsDto.setDate(yesterdayStr);
            dtos.add(yesterdayStaticsDto);
            AdminActivityStatisticsDto todayStaticsDto = new AdminActivityStatisticsDto();
            todayStaticsDto.setDate(todayStr);
            dtos.add(todayStaticsDto);
        } else if (dtos.size() == 1) {
            AdminActivityStatisticsDto staticsDto = new AdminActivityStatisticsDto();
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
