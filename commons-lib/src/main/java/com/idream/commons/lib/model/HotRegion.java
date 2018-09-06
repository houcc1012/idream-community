package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 热门大社区
 */
@ApiModel("热门大社区")
public class HotRegion {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 社区id
     */
    @ApiModelProperty(value = "社区id")
    private Integer regionId;

    /**
     * 活跃值
     */
    @ApiModelProperty(value = "活跃值")
    private Integer activeValue;

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

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getActiveValue() {
        return activeValue;
    }

    public void setActiveValue(Integer activeValue) {
        this.activeValue = activeValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}