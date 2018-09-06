package com.idream.commons.lib.dto.activity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "关联二级活动标签")
public class SecondActivityTagDto {

    @ApiModelProperty(value = "一级标签id")
    private Integer id;

    @ApiModelProperty(value = "关联的二级标签集合")
    private List<Integer> activityTagList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getActivityTagList() {
        return activityTagList;
    }

    public void setActivityTagList(List<Integer> activityTagList) {
        this.activityTagList = activityTagList;
    }
}
