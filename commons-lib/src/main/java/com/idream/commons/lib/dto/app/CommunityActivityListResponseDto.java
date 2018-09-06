package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "社区和小区信息返回dto")
public class CommunityActivityListResponseDto {

    @ApiModelProperty(value = "社区id")
    private Integer regionId;

    @ApiModelProperty(value = "社区名")
    private String regionName;

    @ApiModelProperty(value = "经度")
    private BigDecimal regionLongitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal regionLatitude;

    @ApiModelProperty(value = "小区id")
    private Integer communityId;

    @ApiModelProperty(value = "小区名")
    private String communityName;

    @ApiModelProperty(value = "小区经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "小区纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "button展示状态 0:1:2 | 不显示我的社区button : 显示我的社区button : 显示未添加我的社区button")
    private Integer displayButton;

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

    public BigDecimal getRegionLongitude() {
        return regionLongitude;
    }

    public void setRegionLongitude(BigDecimal regionLongitude) {
        this.regionLongitude = regionLongitude;
    }

    public BigDecimal getRegionLatitude() {
        return regionLatitude;
    }

    public void setRegionLatitude(BigDecimal regionLatitude) {
        this.regionLatitude = regionLatitude;
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

    public Integer getDisplayButton() {
        return displayButton;
    }

    public void setDisplayButton(Integer displayButton) {
        this.displayButton = displayButton;
    }
}
