package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 活动类别表
 */
@ApiModel("活动类别表")
public class ActivityType {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 类别名称
     */
    @ApiModelProperty(value = "类别名称")
    private String name;

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
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * 高亮图片
     */
    @ApiModelProperty(value = "高亮图片")
    private String iconLight;

    /**
     * 背景
     */
    @ApiModelProperty(value = "背景")
    private String background;

    /**
     * 分类,1主类,2辅类
     */
    @ApiModelProperty(value = "分类,1主类,2辅类")
    private Byte kind;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getIconLight() {
        return iconLight;
    }

    public void setIconLight(String iconLight) {
        this.iconLight = iconLight == null ? null : iconLight.trim();
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background == null ? null : background.trim();
    }

    public Byte getKind() {
        return kind;
    }

    public void setKind(Byte kind) {
        this.kind = kind;
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