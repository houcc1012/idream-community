package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author: houcc
 * @Date: 2018/7/20
 */
public class AppUserRegionDto {

    @ApiModelProperty(value = "社区ID")
    private Integer regionId;

    @ApiModelProperty(value = "社区名称")
    private String regionName;

    @ApiModelProperty(value = "社区关联的书屋,逗号隔开")
    private String bookNames;

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

    public String getBookNames() {
        return bookNames;
    }

    public void setBookNames(String bookNames) {
        this.bookNames = bookNames;
    }
}
