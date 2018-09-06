package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Author: jeffery
 * @Date: 2018/7/10 10:16
 */
@ApiModel(value = "书屋关联社区请求参数dto")
public class BookConnectRegionParams {
    @ApiModelProperty(value = "书屋id")
    private Integer groupId;

    @ApiModelProperty(value = "社区id")
    @NotNull(message = "社区不能为空 或 所输入的社区不存在...")
    private Integer regionId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
}
