package com.idream.commons.lib.dto.app;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "(app端) 附近的社区")
public class NeighborCommunityListResponseDto {

    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    @ApiModelProperty(value = "社区名称")
    private String communityName;

    @ApiModelProperty(value = "社区经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "社区纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "距离")
    private BigDecimal distance;

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
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

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

}
