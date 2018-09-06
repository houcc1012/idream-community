package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "添加社区请求参数")
public class AddCommunityRequestDto {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

}
