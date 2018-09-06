package com.idream.commons.lib.dto.achievement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author charles
 */
@ApiModel("成就信息显示")
public class AchievementPageDto {
    @ApiModelProperty("成就id")
    private Integer achievementId;
    @ApiModelProperty("成就名称")
    private String achievementName;
    @ApiModelProperty("成就描述")
    private String description;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("规则说明")
    private String instructions;
    @ApiModelProperty("人数")
    private Integer userCount;
    @ApiModelProperty("成就奖励")
    private String awardNames;
    @ApiModelProperty("时间类型:对应时效,1永久,2固定时间")
    private Integer timeType;
    @ApiModelProperty("时间类型是2时,有用,开始时间")
    private Date startTime;
    @ApiModelProperty("时间类型是2时,有用,结束时间")
    private Date endTime;
    @ApiModelProperty("成就状态,1正常,2过期")
    private Integer achievementStatus;
    @ApiModelProperty("成就类型id")
    private Integer categoryId;
    @ApiModelProperty("成就类型")
    private String categoryName;
    @ApiModelProperty("上下架状态,1上架,2下架")
    private Integer status;
    @ApiModelProperty("分数值")
    private Integer score;

    public String getAwardNames() {
        return awardNames;
    }

    public void setAwardNames(String awardNames) {
        this.awardNames = awardNames;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getAchievementStatus() {
        return achievementStatus;
    }

    public void setAchievementStatus(Integer achievementStatus) {
        this.achievementStatus = achievementStatus;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getAchievementId() {

        return achievementId;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getInstructions() {
        return instructions;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
