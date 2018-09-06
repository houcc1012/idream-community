package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/6/9 13:25
 */
@ApiModel(value = "banner图片参数")
public class AdminBannerImageDto {
    @ApiModelProperty(value = "banner设置id")
    private Integer bannerId;

    @ApiModelProperty(value = "banner说明")
    private String description;

    @ApiModelProperty(value = "投放位置")
    private Integer category;

    @ApiModelProperty(value = "banner类型名")
    private String name;

    @ApiModelProperty(value = "查看图片 图片链接")
    private String imageUrl;

    @ApiModelProperty(value = "状态")
    private Integer displayEnable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getDisplayEnable() {
        return displayEnable;
    }

    public void setDisplayEnable(Integer displayEnable) {
        this.displayEnable = displayEnable;
    }
}
