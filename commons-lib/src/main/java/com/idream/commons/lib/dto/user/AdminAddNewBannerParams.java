package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: jeffery
 * @Date: 2018/6/9 10:24
 */
@ApiModel(value = "新增banner请求参数")
public class AdminAddNewBannerParams {

    @ApiModelProperty(value = "banner类型")
    @NotNull(message = "投放位置不能为空")
    private Integer typeId;

    @ApiModelProperty(value = "banner说明")
    @NotBlank(message = "图片说明不能为空")
    private String description;
    @ApiModelProperty("类型")
    private Byte type;

    @ApiModelProperty("图片链接")
    private String imageUrl;

    @ApiModelProperty("跳转链接")
    private String jumpLink;


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
