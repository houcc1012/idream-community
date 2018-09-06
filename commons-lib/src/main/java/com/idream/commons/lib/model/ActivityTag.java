package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 活动标签表
 */
@ApiModel("活动标签表")
public class ActivityTag {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 标签名
     */
    @ApiModelProperty(value = "标签名")
    private String label;

    /**
     * 父级默认0
     */
    @ApiModelProperty(value = "父级默认0")
    private Integer pid;

    /**
     * 管理员id
     */
    @ApiModelProperty(value = "管理员id")
    private Integer adminUserId;

    /**
     * 管理员昵称
     */
    @ApiModelProperty(value = "管理员昵称")
    private String adminNickName;

    /**
     * 状态,1正常,2删除
     */
    @ApiModelProperty(value = "状态,1正常,2删除")
    private Byte status;

    /**
     * 级别
     */
    @ApiModelProperty(value = "级别")
    private Integer level;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getAdminNickName() {
        return adminNickName;
    }

    public void setAdminNickName(String adminNickName) {
        this.adminNickName = adminNickName == null ? null : adminNickName.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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