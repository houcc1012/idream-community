package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/7/6 10:35
 */
@ApiModel(value = "app端 小区信息")
public class CommunityDto {
    @ApiModelProperty(value = "小区id")
    private Integer communityId;

    @ApiModelProperty(value = "小区名")
    private String communityName;

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
