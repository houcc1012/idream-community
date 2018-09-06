package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles.wei
 */
@ApiModel("社区用户认证参数")
public class AdminCommunityAuthParams extends PagesParam {
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("地区编码")
    private String adCode;
    @ApiModelProperty("社区名")
    private String communityName;
    @ApiModelProperty("身份类型,1原住民,2泊客,3书虫")
    private Integer userType;
    @ApiModelProperty("认证状态,1等待,2通过,3失败")
    private Integer authStatus;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
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

    public String getAdCode() {
        return adCode;
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
