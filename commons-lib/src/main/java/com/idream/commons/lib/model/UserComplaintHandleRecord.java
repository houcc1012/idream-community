package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户处理记录
 */
@ApiModel("用户处理记录")
public class UserComplaintHandleRecord {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 投诉编号
     */
    @ApiModelProperty(value = "投诉编号")
    private Integer complaintId;

    /**
     * 业务id
     */
    @ApiModelProperty(value = "业务id")
    private Integer businessId;

    /**
     * 默认1用户,2群
     */
    @ApiModelProperty(value = "默认1用户,2群")
    private Byte businessType;

    /**
     * 管理员id
     */
    @ApiModelProperty(value = "管理员id")
    private Integer adminUserId;

    /**
     * 处理类型,禁言:ban
     */
    @ApiModelProperty(value = "处理类型,禁言:ban")
    private String handleCode;

    /**
     * 处理名称
     */
    @ApiModelProperty(value = "处理名称")
    private String handleName;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 处理描述
     */
    @ApiModelProperty(value = "处理描述")
    private String description;

    /**
     * 状态,1开始,2结束
     */
    @ApiModelProperty(value = "状态,1开始,2结束")
    private Byte status;

    /**
     * 生成时间
     */
    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Byte getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Byte businessType) {
        this.businessType = businessType;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getHandleCode() {
        return handleCode;
    }

    public void setHandleCode(String handleCode) {
        this.handleCode = handleCode == null ? null : handleCode.trim();
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName == null ? null : handleName.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}