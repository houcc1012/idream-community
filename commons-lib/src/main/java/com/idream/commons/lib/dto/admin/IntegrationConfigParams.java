package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 积分配置表
 */
@ApiModel("积分配置参数表")
public class IntegrationConfigParams {

    @ApiModelProperty(value = "打卡积分")
    @NotNull(message = "打卡积分不能为空")
    @Min(value = 0, message = "打卡积分最小值必须大于等于0")
    private Integer signScore;

    @ApiModelProperty(value = "首次分享积分")
    @NotNull(message = "首次分享积分不能为空")
    @Min(value = 0, message = "首次分享积分最小值必须大于等于0")
    private Integer firstShareScore;

    @ApiModelProperty(value = "非首次分享积分")
    @NotNull(message = "非首次分享积分不能为空")
    @Min(value = 0, message = "非首次分享积分最小值必须大于等于0")
    private Integer shareScore;

    @ApiModelProperty(value = "分享上限积分")
    @NotNull(message = "分享上限积分不能为空")
    @Min(value = 0, message = "分享上限积分最小值必须大于等于0")
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