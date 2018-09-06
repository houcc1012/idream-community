package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Description :
 * @Created by xiaogang on 2018/5/2.
 */
@ApiModel(value = "消息内点赞详情")
public class AppThumbUpDetailDto extends AppNeighborInfoDto {

    @ApiModelProperty("动态ID")
    private Integer lifeId;

    @ApiModelProperty("动态内容")
    private String content;

    @ApiModelProperty("点赞时间")
    private Date createTime;

    @ApiModelProperty(value = "点赞封面")
    private String frontCoverImage;

    public Integer getLifeId() {
        return lifeId;
    }

    public void setLifeId(Integer lifeId) {
        this.lifeId = lifeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFrontCoverImage() {
        return frontCoverImage;
    }

    public void setFrontCoverImage(String frontCoverImage) {
        this.frontCoverImage = frontCoverImage;
    }
}
