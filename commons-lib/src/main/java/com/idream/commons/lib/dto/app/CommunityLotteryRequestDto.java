package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by DELL2018-005 on 2018/5/15.
 */
@ApiModel(value = "社区抽奖活动请求参数")
public class CommunityLotteryRequestDto {

    @ApiModelProperty(value = "大区id", required = false)
    private Integer regionId;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

}
