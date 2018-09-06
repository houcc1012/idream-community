package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles.wei
 */
@ApiModel("社区认证返回实体")
public class AdminCommunityAuthDto {
    @ApiModelProperty("认证id,修改操作传这个参数")
    private Integer authId;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("姓名,认证不填,暂时不用")
    private String realName;
    @ApiModelProperty("所在区域")
    private String location;
    @ApiModelProperty("社区名")
    private String communityName;
    @ApiModelProperty("身份类型,1原住民,2泊客,3书虫")
    private Integer userType;
    @ApiModelProperty("认证状态,1等待,2通过,3失败")
    private Integer authStatus;

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public String getPhone() {
        return phone;
    }

    public String getNickName() {
        return nickName;
    }

    public String getRealName() {
        return realName;
    }

    public String getLocation() {
        return location;
    }

    public String getCommunityName() {
        return communityName;
    }

    public Integer getUserType() {
        return userType;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }
}
