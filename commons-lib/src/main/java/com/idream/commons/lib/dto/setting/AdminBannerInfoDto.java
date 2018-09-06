package com.idream.commons.lib.dto.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author charles
 */
@ApiModel("banner新增修改模型")
public class AdminBannerInfoDto {
    @ApiModelProperty(value = "banner设置id,新增不传")
    private Integer bannerId;

    @ApiModelProperty(value = "banner说明")
    @Size(max = 250, message = "描述250个字符")
    private String description;

    @ApiModelProperty(value = "typeId")
    private Integer typeId;

    @ApiModelProperty("1普通,2活动列表,3活动推荐")
    private Byte type;

    @ApiModelProperty("图片链接")
    @NotBlank(message = "图片不能为空")
    private String imageUrl;

    @ApiModelProperty("跳转链接")
    @Size(max = 100, message = "参数最长100字符")
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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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
}
