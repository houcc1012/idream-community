package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("网易云通信群组")
public class WximGroup {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "网易云IM tid")
    private String tid;

    @ApiModelProperty(value = "群所属业务类型 1-活动")
    private Byte businessType;

    @ApiModelProperty(value = "群所属业务主键")
    private Integer businessId;

    @ApiModelProperty(value = "群名称")
    private String groupName;

    @ApiModelProperty(value = "群公告")
    private String groupAnnouncement;

    @ApiModelProperty(value = "群描述")
    private String groupDesc;

    @ApiModelProperty(value = "群封面头像URL")
    private String imgaeUrl;

    @ApiModelProperty(value = "加群权限 0不用验证，1需要验证,2不允许任何人加入")
    private Byte joinRole;

    @ApiModelProperty(value = "邀请入群权限 0-管理员(默认),1-所有人。")
    private Byte inviteRole;

    @ApiModelProperty(value = "群资料修改权限 0-管理员(默认),1-所有人")
    private Byte updateGroupinfoRole;

    @ApiModelProperty(value = "被邀请人入群权限 0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群")
    private Byte magreeRole;

    @ApiModelProperty(value = "群组禁言类型 0: 无禁言，1:禁言普通成员  3:禁言整个群-包括群主")
    private Byte muteType;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public Byte getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Byte businessType) {
        this.businessType = businessType;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getGroupAnnouncement() {
        return groupAnnouncement;
    }

    public void setGroupAnnouncement(String groupAnnouncement) {
        this.groupAnnouncement = groupAnnouncement == null ? null : groupAnnouncement.trim();
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc == null ? null : groupDesc.trim();
    }

    public String getImgaeUrl() {
        return imgaeUrl;
    }

    public void setImgaeUrl(String imgaeUrl) {
        this.imgaeUrl = imgaeUrl == null ? null : imgaeUrl.trim();
    }

    public Byte getJoinRole() {
        return joinRole;
    }

    public void setJoinRole(Byte joinRole) {
        this.joinRole = joinRole;
    }

    public Byte getInviteRole() {
        return inviteRole;
    }

    public void setInviteRole(Byte inviteRole) {
        this.inviteRole = inviteRole;
    }

    public Byte getUpdateGroupinfoRole() {
        return updateGroupinfoRole;
    }

    public void setUpdateGroupinfoRole(Byte updateGroupinfoRole) {
        this.updateGroupinfoRole = updateGroupinfoRole;
    }

    public Byte getMagreeRole() {
        return magreeRole;
    }

    public void setMagreeRole(Byte magreeRole) {
        this.magreeRole = magreeRole;
    }

    public Byte getMuteType() {
        return muteType;
    }

    public void setMuteType(Byte muteType) {
        this.muteType = muteType;
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