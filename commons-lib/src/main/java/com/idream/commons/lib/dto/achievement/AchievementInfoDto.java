package com.idream.commons.lib.dto.achievement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles
 */
@ApiModel("成就信息显示")
public class AchievementInfoDto {
    @ApiModelProperty("成就id")
    private Integer achievementId;
    @ApiModelProperty("成就名称")
    private String achievementName;
    @ApiModelProperty("成就描述")
    private String description;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("是否完成")
    private Boolean completed;

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Integer getAchievementId() {
        return achievementId;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
