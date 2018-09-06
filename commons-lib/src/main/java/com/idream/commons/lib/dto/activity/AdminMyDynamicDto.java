package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/4/28.
 */
@ApiModel(value = "我的动态列表查询")
public class AdminMyDynamicDto {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "隐私级别 1-公开;")
    private Byte privacyLevel;

    @ApiModelProperty(value = "状态 1-草稿;2-正常;3-已删除")
    private Byte status;

    @ApiModelProperty(value = "来源：1-邻里；2-活动")
    private Byte fromType;

    @ApiModelProperty(value = "生活类型")
    private Integer typeId;

    @ApiModelProperty(value = "活动ID")
    private Integer activityId;

    @ApiModelProperty(value = "活动标题")
    private String activityTitle;

    @ApiModelProperty(value = "发布时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "图片")
    private List<AppImageParam> imageList;

    @ApiModelProperty(value = "点赞数")
    private Integer thumbupNum;

    @ApiModelProperty(value = "评论数")
    private Integer commentNum;

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


//    public String getFrontCoverImage() {
//        return frontCoverImage;
//    }
//
//    public void setFrontCoverImage(String frontCoverImage) {
//        this.frontCoverImage = frontCoverImage;
//    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getPrivacyLevel() {
        return privacyLevel;
    }

    public void setPrivacyLevel(Byte privacyLevel) {
        this.privacyLevel = privacyLevel;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getThumbupNum() {
        return thumbupNum;
    }

    public void setThumbupNum(Integer thumbupNum) {
        this.thumbupNum = thumbupNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Byte getFromType() {
        return fromType;
    }

    public void setFromType(Byte fromType) {
        this.fromType = fromType;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
}
