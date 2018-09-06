package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 区域社区关联表
 */
@ApiModel("区域社区关联表")
public class CommunityRegionRelation {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 区域id
     */
    @ApiModelProperty(value = "区域id")
    private Integer regionId;

    /**
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private Integer groupId;

    /**
     * 1官方,10省,20市,30区,40社区,60小区,,70书屋,100个人
     */
    @ApiModelProperty(value = "1官方,10省,20市,30区,40社区,60小区,,70书屋,100个人")
    private Byte groupType;

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

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Byte getGroupType() {
        return groupType;
    }

    public void setGroupType(Byte groupType) {
        this.groupType = groupType;
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