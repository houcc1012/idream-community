package com.idream.commons.lib.dto.achievement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("app成就奖励参数")
public class AppAchievementAwardParams {
    @ApiModelProperty("成就id")
    private Integer achievementId;
    @ApiModelProperty(hidden = true)
    private Integer authUserId;

    public Integer getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public Integer getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(Integer authUserId) {
        this.authUserId = authUserId;
    }
}
