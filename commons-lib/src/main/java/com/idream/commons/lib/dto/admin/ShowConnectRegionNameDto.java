package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/6/13 10:56
 */
@ApiModel(value = "关联社区 社区列表显示(管理端)返回参数")
public class ShowConnectRegionNameDto {

    @ApiModelProperty(value = "社区id")
    private Integer regionId;

    @ApiModelProperty(value = "社区名称")
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
