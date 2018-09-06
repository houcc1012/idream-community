package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 */
@ApiModel("用户认证记录")
public class UserCommunityRelationRecord {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 认证id
     */
    @ApiModelProperty(value = "认证id")
    private Integer authId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 社区id
     */
    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    /**
     * 审核状态:1审核中,2审核通过,3不通过
     */
    @ApiModelProperty(value = "审核状态:1审核中,2审核通过,3不通过")
    private Byte status;

    /**
     * 绑定类型:1,普通认证,2为高级认证
     */
    @ApiModelProperty(value = "绑定类型:1,普通认证,2为高级认证")
    private Byte type;

    /**
     * 户主类型:1,业主,2租户,3嘉宾
     */
    @ApiModelProperty(value = "户主类型:1,业主,2租户,3嘉宾")
    private Byte userType;

    /**
     * 认证来源:1,位置,2书屋
     */
    @ApiModelProperty(value = "认证来源:1,位置,2书屋")
    private Byte fromType;

    /**
     * 管理员id
     */
    @ApiModelProperty(value = "管理员id")
    private Integer adminUserId;

    /**
     * 处理结果,2通过,3拒绝
     */
    @ApiModelProperty(value = "处理结果,2通过,3拒绝")
    private Byte handleResult;

    /**
     * 处理时间
     */
    @ApiModelProperty(value = "处理时间")
    private Date handleTime;

    /**
     * 是否删除,1删除,0未删除
     */
    @ApiModelProperty(value = "是否删除,1删除,0未删除")
    private Boolean deleted;

    /**
     * 删除时间
     */
    @ApiModelProperty(value = "删除时间")
    private Date deletedTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String description;

    /**
     * 生成时间
     */
    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public Byte getFromType() {
        return fromType;
    }

    public void setFromType(Byte fromType) {
        this.fromType = fromType;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public Byte getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(Byte handleResult) {
        this.handleResult = handleResult;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Date deletedTime) {
        this.deletedTime = deletedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}