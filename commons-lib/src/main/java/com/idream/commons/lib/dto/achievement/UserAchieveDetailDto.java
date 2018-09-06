package com.idream.commons.lib.dto.achievement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("用户成就详情")
public class UserAchieveDetailDto {
    @ApiModelProperty("成就名")
    private String achievementName;
    @ApiModelProperty("是否完成")
    private Boolean completed;
    @ApiModelProperty("完成时间")
    private Date completeTime;
    @ApiModelProperty("完成排名")
    private Integer completeNum;
    @ApiModelProperty("总完成数")
    private Integer totalNum;
    @ApiModelProperty("完成说明")
    private String instructions;
    @ApiModelProperty("图片")
    private String icon;
    @ApiModelProperty("已完成数量")
    private Integer nowStep;
    @ApiModelProperty("总完成数量")
    private Integer totalStep;

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Integer getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Integer completeNum) {
        this.completeNum = completeNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getNowStep() {
        return nowStep;
    }

    public void setNowStep(Integer nowStep) {
        this.nowStep = nowStep;
    }

    public Integer getTotalStep() {
        return totalStep;
    }

    public void setTotalStep(Integer totalStep) {
        this.totalStep = totalStep;
    }
}
