package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/6/9 14:48
 */
@ApiModel(value = "微信小程序banner的显示")
public class WXDisplayBannerDto {
    @ApiModelProperty(value = "banner主键")
    private Integer bannerId;

    @ApiModelProperty(value = "banner图片链接")
    private String imageUrl;

    @ApiModelProperty(value = "banner图片跳转")
    private String jumpUrl;

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }
}
