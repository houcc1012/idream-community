package com.idream.commons.lib.dto.activity;

import java.math.BigDecimal;

import com.idream.commons.lib.model.LotteryInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "现场开奖返回dto")
public class LotteryInfoResponseDto extends LotteryInfo {

    @ApiModelProperty(value = "社区经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "社区纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "社区名")
    private String communityName;

    @ApiModelProperty(value = "社区开奖的次数")
    private Integer awardCount;

    @ApiModelProperty(value = "当前定位与开奖社区的距离")
    private double distance;

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

    public Integer getAwardCount() {
        return awardCount;
    }

    public void setAwardCount(Integer awardCount) {
        this.awardCount = awardCount;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }


}
