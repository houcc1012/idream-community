package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("简单用户信息")
public class AppSimpleUserInfoDto {
    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("用户头像")
    private String image;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getImage() {
        return image;
    }
}
