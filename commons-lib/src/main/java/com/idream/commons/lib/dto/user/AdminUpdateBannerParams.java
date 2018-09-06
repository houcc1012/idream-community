package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @Author: jeffery
 * @Date: 2018/6/9 12:15
 */
@ApiModel(value = "修改banner请求参数")
public class AdminUpdateBannerParams {
    @ApiModelProperty(value = "banner主键")
    private Integer bannerId;

    @ApiModelProperty(value = "banner类型")
    @Min(value = 1, message = "类型id请正确选择")
    private Integer typeId;

    @ApiModelProperty(value = "banner说明")
    private String description;

    @ApiModelProperty(value = "banner图片链接和跳转链接")
    private List<AdminBannerUrlParams> adminBannerUrlParams;

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AdminBannerUrlParams> getAdminBannerUrlParams() {
        return adminBannerUrlParams;
    }

    public void setAdminBannerUrlParams(List<AdminBannerUrlParams> adminBannerUrlParams) {
        this.adminBannerUrlParams = adminBannerUrlParams;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

}
