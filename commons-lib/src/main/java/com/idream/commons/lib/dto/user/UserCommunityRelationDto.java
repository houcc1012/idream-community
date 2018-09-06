package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户关联社区信息表
 */
@ApiModel("用户关联社区信息表")
public class UserCommunityRelationDto {
    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "社区ID")
    private Integer communityId;

    @ApiModelProperty(value = "社区名称")
    private String communityName;

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

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}