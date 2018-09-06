package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author: jeffery
 * @Date: 2018/6/8 16:46
 */
@ApiModel(value = "banner图片列表返回参数")
public class AdminBannerImageListDto {

    @ApiModelProperty(value = "banner设置id,新增不传")
    private Integer bannerId;

    @ApiModelProperty(value = "banner说明")
    private String description;

    @ApiModelProperty(value = "typeId")
    private Integer typeId;

    @ApiModelProperty(value = "banner类型名,新增修改都不传")
    private String name;
    @ApiModelProperty("1普通,2活动列表,3活动推荐")
    private Byte type;
    @ApiModelProperty(value = "状态")
    private Boolean displayEnable;

    @ApiModelProperty("图片链接")
    private String imageUrl;

    @ApiModelProperty("跳转链接")
    private String jumpLink;

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Boolean getDisplayEnable() {
        return displayEnable;
    }

    public void setDisplayEnable(Boolean displayEnable) {
        this.displayEnable = displayEnable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
