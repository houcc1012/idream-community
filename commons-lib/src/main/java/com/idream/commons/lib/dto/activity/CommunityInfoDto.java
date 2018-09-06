package com.idream.commons.lib.dto.activity;

import java.math.BigDecimal;

public class CommunityInfoDto {

    private ActivityDataDto activityDataDto;

    private BigDecimal distance;

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public ActivityDataDto getActivityDataDto() {
        return activityDataDto;
    }

    public void setActivityDataDto(ActivityDataDto activityDataDto) {
        this.activityDataDto = activityDataDto;
    }

}
