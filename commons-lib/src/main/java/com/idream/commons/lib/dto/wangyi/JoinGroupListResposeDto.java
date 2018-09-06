package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/18 19:36
 * @Description:
 */
@ApiModel("参与的群组的列表")
public class JoinGroupListResposeDto {

    @ApiModelProperty("群组id")
    private Integer tid;
    @ApiModelProperty("活动id")
    private Integer activityId;
    @ApiModelProperty("群组名字")
    private String groupName;
    @ApiModelProperty("群头像")
    private String icon;
    @ApiModelProperty("群公告")
    private String announcement;
    @ApiModelProperty("群描述")
    private String groupDesc;

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }


}

