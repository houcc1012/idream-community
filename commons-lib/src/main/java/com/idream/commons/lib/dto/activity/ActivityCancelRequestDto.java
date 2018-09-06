package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;


/**
 * @Auther: penghekai
 * @Date: 2018/6/27 14:35
 * @Description:
 */
@ApiModel("取消活动请求参数")
public class ActivityCancelRequestDto {

    @ApiModelProperty("活动id")
    private Integer activityId;
    @Length(min = 1, max = 200, message = "取消原因不为空且不超过200字符")
    @ApiModelProperty("取消原因内容")
    private String content;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

