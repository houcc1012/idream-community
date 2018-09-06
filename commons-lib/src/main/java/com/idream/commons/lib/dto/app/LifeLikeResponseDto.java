package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by DELL2018-005 on 2018/5/18.
 */
@ApiModel(value = "邻里生活点赞返回参数")
public class LifeLikeResponseDto {

    @ApiModelProperty(value = "邻里生活记录id")
    private Integer communityLifeLikeId;

    @ApiModelProperty(value = "邻里生活创建者id")
    private Integer userId;

    @ApiModelProperty(value = "邻里生活创建者图像")
    private String userIconImage;

    @ApiModelProperty(value = "点赞者id")
    private Integer likeId;

    @ApiModelProperty(value = "点赞者昵称")
    private String likeNickName;

    @ApiModelProperty(value = "点赞者图像")
    private String likeIconImage;

    @ApiModelProperty(value = "邻里点赞的内容")
    private String content;

    @ApiModelProperty(value = "邻里点赞内容的图片")
    private String image;

    @ApiModelProperty(value = "点赞时间")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserIconImage() {
        return userIconImage;
    }

    public void setUserIconImage(String userIconImage) {
        this.userIconImage = userIconImage;
    }

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public String getLikeNickName() {
        return likeNickName;
    }

    public void setLikeNickName(String likeNickName) {
        this.likeNickName = likeNickName;
    }

    public String getLikeIconImage() {
        return likeIconImage;
    }

    public void setLikeIconImage(String likeIconImage) {
        this.likeIconImage = likeIconImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCommunityLifeLikeId() {
        return communityLifeLikeId;
    }

    public void setCommunityLifeLikeId(Integer communityLifeLikeId) {
        this.communityLifeLikeId = communityLifeLikeId;
    }
}
