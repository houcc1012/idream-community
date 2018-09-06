package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Auther: penghekai
 * @Date: 2018/7/21 15:42
 * @Description:
 */
@ApiModel("管理端活动操作请求参数")
public class AdminActivityOperateRecordRequestDto {

    @ApiModelProperty(value = "用户id", hidden = true)
    private Integer userId;

    @ApiModelProperty("活动id")
    @NotNull(message = "活动id不能为空")
    private Integer activityId;

    @ApiModelProperty("内容")
    @NotBlank(message = "内容不能为空")
    @Length(min = 1, max = 200, message = "内容不为空且不超过200字符")
    private String content;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

