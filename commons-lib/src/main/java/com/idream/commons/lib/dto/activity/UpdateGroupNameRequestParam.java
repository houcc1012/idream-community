package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;


/**
 * @Auther: penghekai
 * @Date: 2018/7/26 15:58
 * @Description:
 */
@ApiModel("修改群聊名字请求参数")
public class UpdateGroupNameRequestParam {

    @ApiModelProperty("活动id")
    private Integer activityId;

    @ApiModelProperty("群组名称")
    @Length(max = 15, message = "群聊名不超过15个字符")
    private String groupName;

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
}

