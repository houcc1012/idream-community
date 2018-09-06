package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import javax.validation.constraints.NotBlank;

/**
 * @Author: jeffery
 * @Date: 2018/6/9 10:45
 */
@ApiModel(value = "banner图片url和跳转链接")
public class AdminBannerUrlParams {

    @ApiModelProperty(value = "banner图片url")
    @NotBlank(message = "banner图片url不能为空")
    private String imageUrl;

    @ApiModelProperty(value = "banner图片跳转链接")
    private String jumpLink;
    @ApiModelProperty("类型,1链接,2多个活动,3单个活动")
    private Byte type;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getJumpLink() {
        return jumpLink;
    }

    public void setJumpLink(String jumpLink) {
        this.jumpLink = jumpLink;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
