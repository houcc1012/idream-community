package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/4/28.
 */
@ApiModel(value = "动态列表查询结果")
public class AppCommunityLifeDto extends AppNeighborInfoDto {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "隐私级别 1-公开;2-关注我的;3-仅自己;4-获取")
    private Byte privacyLevel;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    @ApiModelProperty(value = "图片")
    private List<AppImageParam> imageList;

    @ApiModelProperty(value = "当前用户是否关注过此用户（0未关注，1已关注）")
    private Integer attended;

    @ApiModelProperty(value = "当前用户是否对此动态点过赞（0未点赞，1已点赞）")
    private Integer thumbuped;

    @ApiModelProperty(value = "当前用户是否参加过活动，返回true和false")
    private Boolean joined;

    @ApiModelProperty(value = "是否是当前用户")
    private Boolean mine;

    @ApiModelProperty(value = "来源：1-邻里；2-活动")
    private Byte fromType;

    @ApiModelProperty(value = "活动ID")
    private Integer activityId;

    @ApiModelProperty(value = "活动状态（1草稿,2创建完成,3发布,4审核通过,5审核不通过,0取消活动）")
    private Integer activityStatus;

    @ApiModelProperty(value = "生活类型")
    private Integer typeId;

    @ApiModelProperty(value = "活动标题")
    private String activityTitle;

    @ApiModelProperty(value = "点赞数")
    private String thumbupNum;

    @ApiModelProperty(value = "用户标签")
    private List<AppUserLabelDto> userLabelList;

    @ApiModelProperty(value = "点赞人详情")
    private List<AppNeighborInfoDto> neighborInfoList;

    public AppCommunityLifeDto() {
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

    public Boolean getMine() {
        return mine;
    }

    public void setMine(Boolean mine) {
        this.mine = mine;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getThumbupNum() {
        return thumbupNum;
    }

    public void setThumbupNum(String thumbupNum) {
        this.thumbupNum = thumbupNum;
    }

    public Integer getAttended() {
        return attended;
    }

    public void setAttended(Integer attended) {
        this.attended = attended;
    }

    public Integer getThumbuped() {
        return thumbuped;
    }

    public void setThumbuped(Integer thumbuped) {
        this.thumbuped = thumbuped;
    }

    public List<AppUserLabelDto> getUserLabelList() {
        return userLabelList;
    }

    public void setUserLabelList(List<AppUserLabelDto> userLabelList) {
        this.userLabelList = userLabelList;
    }

    public List<AppNeighborInfoDto> getNeighborInfoList() {
        return neighborInfoList;
    }

    public void setNeighborInfoList(List<AppNeighborInfoDto> neighborInfoList) {
        this.neighborInfoList = neighborInfoList;
    }

    public Boolean getJoined() {
        return joined;
    }

    public void setJoined(Boolean joined) {
        this.joined = joined;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }
}
