package com.idream.commons.lib.dto.wangyi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/5/29 18:20
 * @Description:
 */
@ApiModel("根据tid查询activityId")
public class QueryActivityIdByTidResponseDto {

    @ApiModelProperty("群组id")
    private String activityId;

    public QueryActivityIdByTidResponseDto() {
    }

    public QueryActivityIdByTidResponseDto(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
}

