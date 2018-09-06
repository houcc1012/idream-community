package com.idream.service;

import com.idream.commons.lib.dto.JSONPublicParam;
import com.idream.commons.lib.dto.PagesDto;
import com.idream.commons.lib.dto.adminuser.AdminComplaintDto;
import com.idream.commons.lib.dto.adminuser.AdminComplaintHandleBanParams;
import com.idream.commons.lib.dto.adminuser.AdminComplaintHandleStatusDto;
import com.idream.commons.lib.dto.adminuser.AdminComplaintParams;
import com.idream.commons.lib.dto.user.AppComplaintParams;
import com.idream.commons.lib.dto.user.AppComplaintTypeDto;
import com.idream.commons.lib.enums.UserEnum;

import java.util.List;

public interface UserComplaintService {
    /**
     * 返回投诉类型
     *
     * @param typeId
     */
    List<AppComplaintTypeDto> listComplaintType(Integer typeId);

    /**
     * 保存投诉
     *
     * @param params
     */
    void saveComplaint(JSONPublicParam<AppComplaintParams> params);

    /**
     * 查询的所有举报
     *
     * @param query
     */
    PagesDto<AdminComplaintDto> getUserComplaintByQuery(AdminComplaintParams query);

    /**
     * 处理状态
     *
     * @param complaintId
     * @param code
     *
     * @see UserEnum.UserComplaintStatus
     */
    void updateComplaintStatus(Integer complaintId, Byte code);

    /**
     * 添加禁言
     *
     * @param param
     */
    Integer addHandleBan(JSONPublicParam<AdminComplaintHandleBanParams> param);

    /**
     * 查询状态
     *
     * @param handleId
     */
    AdminComplaintHandleStatusDto getHandleStatus(Integer handleId);

    /**
     * 修改处理的状态
     *
     * @param handleId
     * @param code
     *
     * @see com.idream.commons.lib.enums.UserEnum.UserComplaintHandleStatus
     */
    void updateHandleStatus(Integer handleId, Byte code);
}
