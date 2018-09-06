package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.user.UserTagDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/4/28.
 */
@ApiModel(value = "动态详情查询结果")
public class AppCommunityLifeDetailDto extends AppNeighborInfoDto {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "内容")
    private String content;

    //    @ApiModelProperty(value = "隐私级别")
//    private Integer privacyLevel;
//
    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    @ApiModelProperty(value = "来源：1-邻里；2-活动")
    private Byte fromType;

    @ApiModelProperty(value = "活动ID")
    private Integer activityId;

    @ApiModelProperty(value = "生活类型")
    private Integer typeId;

    @ApiModelProperty(value = "活动标题")
    private String activityTitle;

    @ApiModelProperty(value = "图片")
    private List<AppImageParam> imageList;

    @ApiModelProperty(value = "点赞人信息")
    private List<AppNeighborInfoDto> NeighborInfoList;

    @ApiModelProperty(value = "点赞数")
    private String thumbupNum;

    @ApiModelProperty(value = "是否点过赞(0否，1是)")
    private Integer thumbuped;

    @ApiModelProperty(value = "用户标签")
    private List<UserTagDto> userLabelList;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<AppImageParam> getImageList() {
        return imageList;
    }

    public void setImageList(List<AppImageParam> imageList) {
        this.imageList = imageList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getPrivacyLevel() {
//        return privacyLevel;
//    }
//
//    public void setPrivacyLevel(Integer privacyLevel) {
//        this.privacyLevel = privacyLevel;
//    }
//
//    public Integer getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Integer updateTime) {
//        this.updateTime = updateTime;
//    }

    public List<AppNeighborInfoDto> getNeighborInfoList() {
        return NeighborInfoList;
    }

    public void setNeighborInfoList(List<AppNeighborInfoDto> neighborInfoList) {
        NeighborInfoList = neighborInfoList;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public List<UserTagDto> getUserLabelList() {
        return userLabelList;
    }

    public void setUserLabelList(List<UserTagDto> userLabelList) {
        this.userLabelList = userLabelList;
    }

    public Integer getThumbuped() {
        return thumbuped;
    }

    public void setThumbuped(Integer thumbuped) {
        this.thumbuped = thumbuped;
    }

    public String getThumbupNum() {
        return thumbupNum;
    }

    public void setThumbupNum(String thumbupNum) {
        this.thumbupNum = thumbupNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
