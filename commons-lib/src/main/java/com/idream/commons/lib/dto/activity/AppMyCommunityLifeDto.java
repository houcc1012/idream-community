package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/4/28.
 */
@ApiModel(value = "我的动态列表查询结果")
public class AppMyCommunityLifeDto extends AppNeighborInfoDto {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "隐私级别 1-公开;2-关注我的;3-仅自己;4-获取；5-选中社区")
    private Byte privacyLevel;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    @ApiModelProperty(value = "图片")
    private List<AppImageParam> imageList;

    @ApiModelProperty(value = "当前用户是否对此动态点过赞（0未点赞，1已点赞）")
    private Integer thumbuped;

    @ApiModelProperty(value = "类型")
    private Integer typeId;

    @ApiModelProperty(value = "来源：1-邻里；2-活动")
    private Byte fromType;

    @ApiModelProperty(value = "活动ID")
    private Integer activityId;

    @ApiModelProperty(value = "活动标题")
    private String activityTitle;

    @ApiModelProperty(value = "点赞数")
    private String thumbupNum;

    @ApiModelProperty(value = "社区信息")
    private List<AppCommunityInfoDto> communityList;

    public AppMyCommunityLifeDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getPrivacyLevel() {
        return privacyLevel;
    }

    public void setPrivacyLevel(Byte privacyLevel) {
        this.privacyLevel = privacyLevel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<AppImageParam> getImageList() {
        return imageList;
    }

    public void setImageList(List<AppImageParam> imageList) {
        this.imageList = imageList;
    }

    public Byte getFromType() {
        return fromType;
    }

    public void setFromType(Byte fromType) {
        this.fromType = fromType;
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

    public String getThumbupNum() {
        return thumbupNum;
    }

    public void setThumbupNum(String thumbupNum) {
        this.thumbupNum = thumbupNum;
    }

    public Integer getThumbuped() {
        return thumbuped;
    }

    public void setThumbuped(Integer thumbuped) {
        this.thumbuped = thumbuped;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<AppCommunityInfoDto> getCommunityList() {
        return communityList;
    }

    public void setCommunityList(List<AppCommunityInfoDto> communityList) {
        this.communityList = communityList;
    }
}
