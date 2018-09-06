package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles.wei
 */
@ApiModel("用户成就展示类")
public class AppUserAchievementDto {
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("成就名称")
    private String achievementName;
    @ApiModelProperty("称号id")
    private Integer achievementId;
    @ApiModelProperty("是否点亮,点亮true")
    private Boolean completed;
    @ApiModelProperty("是否领取")
    private Boolean received;
    @ApiModelProperty(hidden = true)
    private Integer categoryId;

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getIcon() {
        return icon;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public Integer getAchievementId() {
        return achievementId;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public Boolean getReceived() {
        return received;
    }

    public void setReceived(Boolean received) {
        this.received = received;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
