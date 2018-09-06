package com.idream.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.idream.commons.lib.base.RetCodeConstants;
import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.adminuser.AdminComplaintDto;
import com.idream.commons.lib.dto.adminuser.AdminComplaintHandleBanParams;
import com.idream.commons.lib.dto.adminuser.AdminComplaintHandleStatusDto;
import com.idream.commons.lib.dto.adminuser.AdminComplaintParams;
import com.idream.commons.lib.dto.token.AuthUserInfo;
import com.idream.commons.lib.dto.user.AppComplaintParams;
import com.idream.commons.lib.dto.user.AppComplaintTypeDto;
import com.idream.commons.lib.enums.UserEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.UserComplaintHandleRecordMapper;
import com.idream.commons.lib.mapper.UserComplaintMapper;
import com.idream.commons.lib.mapper.UserComplaintTypeMapper;
import com.idream.commons.lib.mapper.UserInfoMapper;
import com.idream.commons.lib.model.UserComplaint;
import com.idream.commons.lib.model.UserComplaintHandleRecord;
import com.idream.commons.lib.util.DateUtils;
import com.idream.service.UserComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserComplaintServiceImpl implements UserComplaintService {
    @Autowired
    private UserComplaintTypeMapper userComplaintTypeMapper;
    @Autowired
    private UserComplaintMapper userComplaintMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserComplaintHandleRecordMapper userComplaintHandleRecordMapper;

    @Override
    public List<AppComplaintTypeDto> listComplaintType(Integer typeId) {
        return userComplaintTypeMapper.selectComplaintTypeDtoList(typeId);
    }

    @Override
    public void saveComplaint(JSONPublicParam<AppComplaintParams> publicParam) {
        AppComplaintParams params = publicParam.getRequestParam();
        AuthUserInfo info = publicParam.getAuthUserInfo();

        Date date = new Date();
        UserComplaint record = new UserComplaint();
        record.setContent(params.getContent());
        record.setCreateTime(date);
        record.setUpdateTime(date);
        record.setFromUserId(info.getUserId());
        record.setFromUserNickName(info.getNickName());
        record.setBusinessId(params.getBusinessId());
        record.setBusinessType(params.getBusinessType().byteValue());
        record.setTypeId(params.getTypeId());
        record.setStatus(UserEnum.UserComplaintStatus.SUBMIT.getCode());
        record.setImages(params.getImages().isEmpty() ? "" : params.getImages().stream().collect(Collectors.joining(";")));
        userComplaintMapper.insertSelective(record);
    }

    @Override
    public PagesDto<AdminComplaintDto> getUserComplaintByQuery(AdminComplaintParams query) {
        PageHelper.startPage(query.getPage(), query.getRows());
        List<AdminComplaintDto> list = userComplaintMapper.selectAdminComplaintByQuery(query);
        PageInfo<AdminComplaintDto> info = new PageInfo<>(list);
        return new PagesDto<>(info.getList(), info.getTotal(), query.getPage(), query.getRows());
    }

    @Override
    public void updateComplaintStatus(Integer complaintId, Byte code) {
        UserComplaint record = new UserComplaint();
        record.setId(complaintId);
        record.setStatus(code);
        userComplaintMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer addHandleBan(JSONPublicParam<AdminComplaintHandleBanParams> param) {
        AuthUserInfo info = param.getAuthUserInfo();
        AdminComplaintHandleBanParams handle = param.getRequestParam();

        UserComplaintHandleRecord ban = userComplaintHandleRecordMapper.queryUserBanStatus(handle.getUserId());
        if (ban != null) {
            throw new BusinessException(RetCodeConstants.UNKOWN_ERROR, "用户已在禁言中");
        }
        Date date = new Date();
        UserComplaintHandleRecord record = new UserComplaintHandleRecord();
        record.setAdminUserId(info.getUserId());
        record.setComplaintId(0);
        record.setCreateTime(date);
        record.setHandleCode(UserEnum.UserComplaintHandle.HANDLE_BAN.getCode());
        record.setBusinessId(handle.getUserId());
        record.setBusinessType(UserEnum.UserComplaintBussinessType.USER.getCode());
        record.setStartTime(date);
        record.setHandleName("禁言");
        record.setEndTime(DateUtils.getAfterADate(date, handle.getDays()));
        userComplaintHandleRecordMapper.insertSelective(record);
        return record.getId();
    }

    @Override
    public AdminComplaintHandleStatusDto getHandleStatus(Integer handleId) {

        UserComplaintHandleRecord record = userComplaintHandleRecordMapper.selectByPrimaryKey(handleId);
        AdminComplaintHandleStatusDto dto = new AdminComplaintHandleStatusDto();
        dto.setCode(record.getHandleCode());
        Date startTime = record.getStartTime();
        Date endTime = record.getEndTime();
        Date now = new Date();

        int total = DateUtils.betweenDays(startTime, endTime);
        int duration = DateUtils.betweenDays(startTime, now);
        dto.setTotalDays(total);
        dto.setDurationDays(total > duration ? duration : total);
        boolean flag = record.getStatus().intValue() == 2;
        if (!flag) {
            flag = endTime.getTime() < System.currentTimeMillis();
        }
        dto.setFinished(flag);
        return dto;
    }

    @Override
    public void updateHandleStatus(Integer handleId, Byte code) {
        UserComplaintHandleRecord record = new UserComplaintHandleRecord();
        record.setId(handleId);
        record.setStatus(code);
        userComplaintHandleRecordMapper.updateByPrimaryKeySelective(record);
    }
}
