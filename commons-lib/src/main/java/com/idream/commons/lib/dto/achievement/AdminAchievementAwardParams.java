package com.idream.commons.lib.dto.achievement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author charles
 */
@ApiModel("admin成就奖励参数")
public class AdminAchievementAwardParams {
    @ApiModelProperty("成就id")
    private Integer achievementId;

    @NotBlank(message = "值不允许为空")
    @ApiModelProperty("奖励值")
    private String awardValue;

    @ApiModelProperty(hidden = true)
    private Byte type = 2;


    public Integer getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public String getAwardValue() {
        return awardValue;
    }

    public void setAwardValue(String awardValue) {
        this.awardValue = awardValue;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
