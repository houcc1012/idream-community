package com.idream.commons.lib.dto.wangyi;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/15 15:17
 * @Description:
 */
@ApiModel
public class CreateGroupRequestParam {

    @ApiModelProperty("活动id")
    @NotNull(message = "活动id不能为空")
    private Integer activityId;

    @ApiModelProperty("群组标题")
    @Length(max = 15, message = "群聊名不超过15个字符")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }


}

