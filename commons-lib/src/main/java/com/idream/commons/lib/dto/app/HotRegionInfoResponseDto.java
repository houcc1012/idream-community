package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author: jeffery
 * @Date: 2018/7/6 10:44
 */
@ApiModel(value = "app端 热门社区返回参数")
public class HotRegionInfoResponseDto {
    @ApiModelProperty(value = "社区id")
    private Integer regionId;

    @ApiModelProperty(value = "社区名称")
    private String regionName;

    @ApiModelProperty(value = "小区信息")
    List<CommunityDto> communityDtos;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "社区活跃值")
    private Integer activeValue;

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

    public List<CommunityDto> getCommunityDtos() {
        return communityDtos;
    }

    public void setCommunityDtos(List<CommunityDto> communityDtos) {
        this.communityDtos = communityDtos;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getActiveValue() {
        return activeValue;
    }

    public void setActiveValue(Integer activeValue) {
        this.activeValue = activeValue;
    }
}
