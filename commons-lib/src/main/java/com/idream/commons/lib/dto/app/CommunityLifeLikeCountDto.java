package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by DELL2018-005 on 2018/5/18.
 */
@ApiModel("邻里点赞未读数量信息返回dto")
public class CommunityLifeLikeCountDto {

    @ApiModelProperty(value = "邻里点赞未读数量")
    private Integer unCheckedCount;

    @ApiModelProperty(value = "邻里用户icon图片")
    private String image;

    public Integer getUnCheckedCount() {
        return unCheckedCount;
    }

    public void setUnCheckedCount(Integer unCheckedCount) {
        this.unCheckedCount = unCheckedCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
