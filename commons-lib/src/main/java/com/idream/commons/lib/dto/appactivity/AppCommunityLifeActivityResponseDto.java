package com.idream.commons.lib.dto.appactivity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询社区邻里动态和最新活动返回参数")
public class AppCommunityLifeActivityResponseDto {

    @ApiModelProperty("社区邻里生活")
    private List<AppCommunityLifeResponseDto> communityLife;
    @ApiModelProperty("活动信息")
    private List<AppActivityInfoResponseDto> activityInfo;

    public List<AppCommunityLifeResponseDto> getCommunityLife() {
        return communityLife;
    }

    public void setCommunityLife(List<AppCommunityLifeResponseDto> communityLife) {
        this.communityLife = communityLife;
    }

    public List<AppActivityInfoResponseDto> getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(List<AppActivityInfoResponseDto> activityInfo) {
        this.activityInfo = activityInfo;
    }

}
