package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: ZhengGaosheng
 * @date: 2018/7/3 13:50
 * @description:
 */
@ApiModel("活动社群活动信息")
public class MiniActivityAssociationActivityInfo {

    @ApiModelProperty("图片")
    private String image;

    @ApiModelProperty("活动标题")
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

