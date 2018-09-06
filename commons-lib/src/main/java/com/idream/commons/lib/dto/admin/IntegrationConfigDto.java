package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 积分配置表
 */
@ApiModel("积分配置显示")
public class IntegrationConfigDto {

    @ApiModelProperty(value = "签到积分")
    private Integer signScore;

    @ApiModelProperty(value = "首次分享积分")
    private Integer firstShareScore;

    @ApiModelProperty(value = "非首次分享积分")
    private Integer shareScore;

    @ApiModelProperty(value = "分享上限积分")
    private Integer maxShareScore;

    public Integer getSignScore() {
        return signScore;
    }

    public void setSignScore(Integer signScore) {
        this.signScore = signScore;
    }

    public Integer getFirstShareScore() {
        return firstShareScore;
    }

    public void setFirstShareScore(Integer firstShareScore) {
        this.firstShareScore = firstShareScore;
    }

    public Integer getShareScore() {
        return shareScore;
    }

    public void setShareScore(Integer shareScore) {
        this.shareScore = shareScore;
    }

    public Integer getMaxShareScore() {
        return maxShareScore;
    }

    public void setMaxShareScore(Integer maxShareScore) {
        this.maxShareScore = maxShareScore;
    }
}