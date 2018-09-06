package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Created by xiaogang on 2018/4/28.
 */
@ApiModel(value = "我的邻里动态列表详情")
public class AdminMyNeighborDynamicDto {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "隐私级别 1-公开;")
    private Byte privacyLevel;

    @ApiModelProperty(value = "状态 1-草稿;2-正常;3-已删除")
    private Byte status;

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

    @ApiModelProperty(value = "社区信息")
    private List<AppCommunityInfoDto> communityList;

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

    public List<AppCommunityInfoDto> getCommunityList() {
        return communityList;
    }

    public void setCommunityList(List<AppCommunityInfoDto> communityList) {
        this.communityList = communityList;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
