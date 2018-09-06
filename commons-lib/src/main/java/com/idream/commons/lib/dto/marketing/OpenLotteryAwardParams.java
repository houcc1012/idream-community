package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author hejiang
 */
@ApiModel("现场开奖请求参数")
public class OpenLotteryAwardParams {

    @ApiModelProperty(value = "现场开奖活动ID", required = true)
    private Integer lotteryId;

    @NotNull(message = "longitude不能为空")
    private BigDecimal longitude;

    @NotNull(message = "latitude不能为空")
    private BigDecimal latitude;

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
