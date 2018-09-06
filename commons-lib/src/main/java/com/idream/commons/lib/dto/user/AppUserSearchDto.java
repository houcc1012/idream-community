package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author charles
 */
@ApiModel("用户搜索返回模型")
public class AppUserSearchDto {
    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("头像")
    private String image;
    @ApiModelProperty("参加活动数")
    private Integer activityCount;
    @ApiModelProperty("粉丝数")
    private Integer fansCount;
    @ApiModelProperty("是否关注")
    private Boolean attention;
    @ApiModelProperty("对方是否关注")
    private Boolean targetAttention;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(Integer activityCount) {
        this.activityCount = activityCount;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Boolean getAttention() {
        return attention;
    }

    public void setAttention(Boolean attention) {
        this.attention = attention;
    }

    public Boolean getTargetAttention() {
        return targetAttention;
    }

    public void setTargetAttention(Boolean targetAttention) {
        this.targetAttention = targetAttention;
    }
}
