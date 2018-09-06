package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/17 20:12
 * @Description:
 */
@ApiModel("活动群信息")
public class ActivityGroupInfoResponseDto {

    @ApiModelProperty("群组图标")
    private String groupIcon;
    @ApiModelProperty("群名称")
    private String groupName;
    @ApiModelProperty("群公告")
    private String groupAnnounceme;
    @ApiModelProperty("群描述")
    private String groupDesc;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    @ApiModelProperty("活动id")
    private Integer activityId;


    @ApiModelProperty("群成员")
    List<GrroupMember> members;

    public String getGroupIcon() {
        return groupIcon;
    }

    public void setGroupIcon(String groupIcon) {
        this.groupIcon = groupIcon;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupAnnounceme() {
        return groupAnnounceme;
    }

    public void setGroupAnnounceme(String groupAnnounceme) {
        this.groupAnnounceme = groupAnnounceme;
    }

    public List<GrroupMember> getMembers() {
        return members;
    }

    public void setMembers(List<GrroupMember> members) {
        this.members = members;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }


}

