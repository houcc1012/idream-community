package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("现场开奖返回参数")
public class OpenLotteryAwardDto {

    @ApiModelProperty(value = "奖品名称")
    private String awardName;

    @ApiModelProperty(value = "剩余开奖次数")
    private int remainCount = 0;

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public int getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(int remainCount) {
        this.remainCount = remainCount;
    }
}
