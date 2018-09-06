package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author: jeffery
 * @Date: 2018/7/17 11:11
 */
@ApiModel(value = "app端 附近活动趣聊返回dto")
public class NeighborChatDto {

    @ApiModelProperty(value = "网易云IM 群id")
    private String tid;

    @ApiModelProperty(value = "群组名")
    private String groupName;

    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    @ApiModelProperty(value = "活动副标题")
    private String subTitle;

    @ApiModelProperty(value = "活动状态 1:2 | 活动进行中:活动未开始")
    private Integer activityStatus;

    @ApiModelProperty(value = "群 image")
    private List<String> imageList;

    @ApiModelProperty(value = "群默认封面url")
    private String imageUrl;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
