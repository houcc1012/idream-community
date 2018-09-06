package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @Description :图片信息
 * @Created by xiaogang on 2018/4/28.
 */
@ApiModel(value = "图片信息")
public class AppImageParam {

    @ApiModelProperty(value = "ID", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "图片路径")
    private String imageUrl;

    @ApiModelProperty(value = "是否封面")
    private Boolean coverImg;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public Boolean getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(Boolean coverImg) {
        this.coverImg = coverImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
