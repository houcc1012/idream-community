package com.idream.commons.lib.dto.appactivity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("App邻里生活动态实体")
public class AppCommunityLifeResponseDto {

    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("生成时间")
    private Long createTime;
    @ApiModelProperty("活动内容中的图片")
    private List<String> image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

}
