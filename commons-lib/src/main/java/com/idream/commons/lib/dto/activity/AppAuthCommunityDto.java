package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "认证社区信息")
public class AppAuthCommunityDto {
    @ApiModelProperty("社区id")
    private Integer communityId;
    @ApiModelProperty("社区名")
    private String communityName;
    @ApiModelProperty("认证方式,1位置认证,2书屋验证")
    private Integer authType;
    @ApiModelProperty("户主类型,1原住民,2泊客,3书友,4未知")
    private Integer userType;
    @ApiModelProperty("书屋名")
    private String bookHouseName;
    @ApiModelProperty("认证状态,1认证中,2认证成功,3认证失败")
    private Integer status;

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public void setBookHouseName(String bookHouseName) {
        this.bookHouseName = bookHouseName;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public Integer getAuthType() {
        return authType;
    }

    public Integer getUserType() {
        return userType;
    }

    public String getBookHouseName() {
        return bookHouseName;
    }
}
