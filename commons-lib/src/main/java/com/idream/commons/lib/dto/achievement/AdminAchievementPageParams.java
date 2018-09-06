package com.idream.commons.lib.dto.achievement;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("成就页查询参数")
public class AdminAchievementPageParams extends PagesParam {
    @ApiModelProperty("成就名称")
    private String achievementName;
    @ApiModelProperty("成就状态,1正常,2过期,不传为全部")
    private Integer achievementStatus;

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public Integer getAchievementStatus() {
        return achievementStatus;
    }

    public void setAchievementStatus(Integer achievementStatus) {
        this.achievementStatus = achievementStatus;
    }
}
