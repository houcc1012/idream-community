package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "后台 用户信息返回dto")
public class AdminUserResponseDto {

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}
