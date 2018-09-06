package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author: jeffery
 * @Date: 2018/7/17 13:53
 */
@ApiModel(value = "app端 我的趣聊返回dto")
public class MyChatDto {

    @ApiModelProperty(value = "网易云IM 群id")
    private String tid;

    @ApiModelProperty(value = "群组名")
    private String groupName;

    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    @ApiModelProperty(value = "群描述")
    private String groupDesc;

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

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
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

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
