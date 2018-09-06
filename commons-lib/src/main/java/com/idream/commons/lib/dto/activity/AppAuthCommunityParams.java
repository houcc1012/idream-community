package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "认证接收参数")
public class AppAuthCommunityParams {
    @ApiModelProperty("社区id")
    private Integer communityId;
    @ApiModelProperty("认证方式,1位置认证,2书屋验证")
    private Integer authType;
    @ApiModelProperty("户主类型,1业主,2租户,3嘉宾,4未知")
    private Integer userType = 1;

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public Integer getAuthType() {
        return authType;
    }

    public Integer getUserType() {
        return userType;
    }
}
