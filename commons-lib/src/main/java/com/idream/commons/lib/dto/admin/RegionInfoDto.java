package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/7/10 10:01
 */
@ApiModel(value = "高级社区信息 返回DTO")
public class RegionInfoDto {
    @ApiModelProperty(value = "高级社区id")
    private Integer regionId;

    @ApiModelProperty(value = "高级社区名")
    private String regionName;

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
}
