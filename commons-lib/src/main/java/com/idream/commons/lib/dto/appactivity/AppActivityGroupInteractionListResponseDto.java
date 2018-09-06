package com.idream.commons.lib.dto.appactivity;

import java.util.Date;
import java.util.List;

import com.idream.commons.lib.model.CommunityLifeImage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("活动圈互动排行榜返回参数")
public class AppActivityGroupInteractionListResponseDto {

    @ApiModelProperty("活动id")
    private Integer activityId;
    @ApiModelProperty("活动圈id")
    private Integer lifeId;
    @ApiModelProperty("活动标题")
    private String activityTitle;
    @ApiModelProperty("活动副标题")
    private String subtitle;
    @ApiModelProperty("活动封面")
    private String activityImage;
    @ApiModelProperty("用户头像")
    private String userImage;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("发布时间")
    private Date publishTime;
    @ApiModelProperty("文本内容")
    private String content;
    @ApiModelProperty("邻里生活动态图片")
    private List<CommunityLifeImage> communityLifeImages;
    @ApiModelProperty("成就称号")
    private List<String> achievement;
    @ApiModelProperty("点赞人头像")
    private List<String> thumberImage;
    @ApiModelProperty("点赞数量")
    private Integer likeCount;
    @ApiModelProperty("点赞数量模糊统计")
    private String likeCountNum;
    @ApiModelProperty("是否点赞")
    private boolean thumb;
    @ApiModelProperty("活动类型图片")
    private String icon;

    public Integer getLifeId() {
        return lifeId;
    }

    public void setLifeId(Integer lifeId) {
        this.lifeId = lifeId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(String activityImage) {
        this.activityImage = activityImage;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<CommunityLifeImage> getCommunityLifeImages() {
        return communityLifeImages;
    }

    public void setCommunityLifeImages(List<CommunityLifeImage> communityLifeImages) {
        this.communityLifeImages = communityLifeImages;
    }

    public List<String> getAchievement() {
        return achievement;
    }

    public void setAchievement(List<String> achievement) {
        this.achievement = achievement;
    }

    public List<String> getThumberImage() {
        return thumberImage;
    }

    public void setThumberImage(List<String> thumberImage) {
        this.thumberImage = thumberImage;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getLikeCountNum() {
        return likeCountNum;
    }

    public void setLikeCountNum(String likeCountNum) {
        this.likeCountNum = likeCountNum;
    }

    public boolean isThumb() {
        return thumb;
    }

    public void setThumb(boolean thumb) {
        this.thumb = thumb;
    }

}
