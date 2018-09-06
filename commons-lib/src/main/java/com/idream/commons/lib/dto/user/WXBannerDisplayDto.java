package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author: jeffery
 * @Date: 2018/6/9 14:33
 */
@ApiModel(value = "微信小程序banner显示返回参数")
public class WXBannerDisplayDto {
    @ApiModelProperty(value = "banner主键")
    private Integer bannerId;

    @ApiModelProperty(value = "banner图片链接和跳转链接")
    private List<AdminBannerUrlParams> adminBannerUrlParams;

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public List<AdminBannerUrlParams> getAdminBannerUrlParams() {
        return adminBannerUrlParams;
    }

    public void setAdminBannerUrlParams(List<AdminBannerUrlParams> adminBannerUrlParams) {
        this.adminBannerUrlParams = adminBannerUrlParams;
    }
}
