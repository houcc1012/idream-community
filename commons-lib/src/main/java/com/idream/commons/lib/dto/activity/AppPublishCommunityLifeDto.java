package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "发布的社区动态")
public class AppPublishCommunityLifeDto {
    @ApiModelProperty("生活动态id")
    private Integer lifeId;
    @ApiModelProperty("图片地址")
    private String image;
    @ApiModelProperty("喜欢数量")
    private Integer likeCount;

    public void setLifeId(Integer lifeId) {
        this.lifeId = lifeId;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getLifeId() {
        return lifeId;
    }

    public String getImage() {
        return image;
    }

    public Integer getLikeCount() {
        return likeCount;
    }
}
