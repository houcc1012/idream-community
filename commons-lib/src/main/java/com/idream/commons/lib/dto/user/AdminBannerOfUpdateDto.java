package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/6/9 13:44
 */
@ApiModel(value = "banner编辑参数dto")
public class AdminBannerOfUpdateDto {
    @ApiModelProperty(value = "banner主键")
    private Integer bannerId;

    @ApiModelProperty(value = "banner类型名")
    private String name;

    @ApiModelProperty(value = "banner类型")
    private Integer category;

    @ApiModelProperty(value = "banner说明")
    private String description;

    @ApiModelProperty(value = "banner图片链接")
    private String imageUrl;

    @ApiModelProperty(value = "banner图片跳转链接")
    private String jumpUrl;

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
