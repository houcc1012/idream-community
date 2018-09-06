package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("活动收集信息")
public class ActivityInfoCollectionDto {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "活动id")
    private Integer activityId;
    @ApiModelProperty(value = "信息明细")
    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
