package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/6/5 20:56
 */
@ApiModel(value = "社区指数返回参数")
public class CommunityIndexDto {

    @ApiModelProperty(value = "文娱,默认:100")
    private Integer entertainment = 100;

    @ApiModelProperty(value = "活跃值")
    private Integer activeValue;

    @ApiModelProperty(value = "宝箱,默认:100")
    private Integer treasureBox = 100;

    public Integer getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(Integer entertainment) {
        this.entertainment = entertainment;
    }

    public Integer getActiveValue() {
        return activeValue;
    }

    public void setActiveValue(Integer activeValue) {
        this.activeValue = activeValue;
    }

    public Integer getTreasureBox() {
        return treasureBox;
    }

    public void setTreasureBox(Integer treasureBox) {
        this.treasureBox = treasureBox;
    }
}
