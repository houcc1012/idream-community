package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("活动管理者")
public class ActivityManagerDto {

    @ApiModelProperty("管理者id")
    private Integer userId;
    @ApiModelProperty("姓名")
    private String realName;

    private Boolean defaulted = false;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Boolean getDefaulted() {
        return defaulted;
    }

    public void setDefaulted(Boolean defaulted) {
        this.defaulted = defaulted;
    }

}
