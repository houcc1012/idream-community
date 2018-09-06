package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author charles.wei
 */
@ApiModel("用户主页")
public class AppHomePageDto {
    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("头像地址")
    private String image;
    @ApiModelProperty("活动数量")
    private Integer activityCount;
    @ApiModelProperty("粉丝数")
    private Integer fansCount;
    @ApiModelProperty("赞数")
    private Integer approvalCount;
    @ApiModelProperty("签到数")
    private Integer signCount;
    @ApiModelProperty("成就称号")
    private List<AppUserAchievementDto> achievements;
    @ApiModelProperty("是否关注")
    private Boolean attention = false;
    @ApiModelProperty("对方关注")
    private Boolean targetAttention = false;
    @ApiModelProperty("是否拉黑")
    private Boolean black = false;
    @ApiModelProperty("大区信息,来自同一社区显示一条")
    private List<String> regionNames;
    @ApiModelProperty("关注数")
    private Integer attentionCount;
    @ApiModelProperty("背景图地址")
    private String backgroundImage;
    @ApiModelProperty("签名")
    private String signature;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setActivityCount(Integer activityCount) {
        this.activityCount = activityCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public void setApprovalCount(Integer approvalCount) {
        this.approvalCount = approvalCount;
    }

    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }

    public void setAchievements(List<AppUserAchievementDto> achievements) {
        this.achievements = achievements;
    }

    public void setAttention(Boolean attention) {
        this.attention = attention;
    }

    public void setRegionNames(List<String> regionNames) {
        this.regionNames = regionNames;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getImage() {
        return image;
    }

    public Integer getActivityCount() {
        return activityCount;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public Integer getApprovalCount() {
        return approvalCount;
    }

    public Integer getSignCount() {
        return signCount;
    }

    public List<AppUserAchievementDto> getAchievements() {
        return achievements;
    }

    public Boolean getAttention() {
        return attention;
    }

    public List<String> getRegionNames() {
        return regionNames;
    }

    public Boolean getBlack() {
        return black;
    }

    public void setBlack(Boolean black) {
        this.black = black;
    }

    public Integer getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(Integer attentionCount) {
        this.attentionCount = attentionCount;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Boolean getTargetAttention() {
        return targetAttention;
    }

    public void setTargetAttention(Boolean targetAttention) {
        this.targetAttention = targetAttention;
    }
}
