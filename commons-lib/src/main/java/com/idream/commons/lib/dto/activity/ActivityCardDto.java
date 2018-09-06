package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Title: ActivityCardParamsDto.java
 * Description:
 * Copyright: Copyright (c) 2017
 * Company: www.idream.com
 *
 * @author huayuliang
 * @version 1.0
 */
@ApiModel(value = "500内打卡")
public class ActivityCardDto {
    @ApiModelProperty(value = "打卡Id")
    private Integer taskId;
    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;
    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskId() {
        return taskId;
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
