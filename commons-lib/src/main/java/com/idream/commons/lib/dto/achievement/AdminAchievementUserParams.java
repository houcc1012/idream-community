package com.idream.commons.lib.dto.achievement;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户成就明细接收参数")
public class AdminAchievementUserParams extends PagesParam {
    @ApiModelProperty("成就id")
    private Integer achievementId;

    public Integer getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ApiModelProperty("手机号")
    private String phone;
}
