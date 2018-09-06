package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * <p>Title: CommunityParams.java</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.idream.com</p >
 *
 * @author penghekai
 * @version 1.0
 */
@ApiModel("查询社区请求参数")
public class CommunityParams {

    @ApiModelProperty(value = "经度", required = true)
    private BigDecimal longtitude;

    @ApiModelProperty(value = "纬度", required = true)
    private BigDecimal latitude;

    public BigDecimal getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(BigDecimal longtitude) {
        this.longtitude = longtitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }


}
