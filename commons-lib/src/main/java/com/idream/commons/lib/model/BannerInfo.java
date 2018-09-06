package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * banner设置
 */
@ApiModel("banner设置")
public class BannerInfo {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 操作人id
     */
    @ApiModelProperty(value = "操作人id")
    private Integer userId;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    private String realName;

    /**
     * 类型id
     */
    @ApiModelProperty(value = "类型id")
    private Integer typeId;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 上下架
     */
    @ApiModelProperty(value = "上下架")
    private Boolean displayEnable;

    /**
     * 类型,1普通,2活动列表,3活动推荐
     */
    @ApiModelProperty(value = "类型,1普通,2活动列表,3活动推荐")
    private Byte type;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private String imageUrl;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private String jumpLink;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sorted;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getDisplayEnable() {
        return displayEnable;
    }

    public void setDisplayEnable(Boolean displayEnable) {
        this.displayEnable = displayEnable;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getJumpLink() {
        return jumpLink;
    }

    public void setJumpLink(String jumpLink) {
        this.jumpLink = jumpLink == null ? null : jumpLink.trim();
    }

    public Integer getSorted() {
        return sorted;
    }

    public void setSorted(Integer sorted) {
        this.sorted = sorted;
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