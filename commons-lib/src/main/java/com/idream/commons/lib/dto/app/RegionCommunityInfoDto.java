package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Author: jeffery
 * @Date: 2018/6/8 14:05
 */
@ApiModel(value = "大社区小社区信息返回参数")
public class RegionCommunityInfoDto {

    @ApiModelProperty(value = "大社区id")
    private Integer regionId;

    @ApiModelProperty(value = "大社区名")
    private String regionName;

    @ApiModelProperty(value = "小社区id")
    private Integer communityId;

    @ApiModelProperty(value = "小社区名")
    private String communityName;

    @ApiModelProperty(value = "小社区经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "小社区纬度")
    private BigDecimal latitude;

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

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

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
}
