package com.idream.commons.lib.dto.achievement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("成就修改参数")
public class AdminAchievementUpdateParams {
    @ApiModelProperty("成就id")
    private Integer achievementId;

    public Integer getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }
}
