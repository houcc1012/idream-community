package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/6/28 14:02
 */
@ApiModel(value = "社区下的小区信息")
public class CommunityInfoParams {

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
