package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/6/5 21:00
 */
@ApiModel(value = "社区指数请求参数")
public class CommunityIndexParams {

    @ApiModelProperty(value = "大社区id")
    private Integer regionId;

    @ApiModelProperty(value = "小社区id")
    private Integer communityId;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }
}
