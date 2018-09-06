package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/6/13 14:43
 */
@ApiModel(value = "高级社区列表返回参数(管理端)")
public class RegionListDto {

    @ApiModelProperty(value = "社区id")
    private Integer regionId;

    @ApiModelProperty(value = "社区名")
    private String regionName;

    @ApiModelProperty(value = "社区icon")
    private String icon;

    @ApiModelProperty(value = "所在区域")
    private String address;

    @ApiModelProperty(value = "关联小区数量")
    private Integer connectCommunityCount;

    @ApiModelProperty(value = "关联书屋数量")
    private Integer connectBookHouseCount;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getConnectCommunityCount() {
        return connectCommunityCount;
    }

    public void setConnectCommunityCount(Integer connectCommunityCount) {
        this.connectCommunityCount = connectCommunityCount;
    }

    public Integer getConnectBookHouseCount() {
        return connectBookHouseCount;
    }

    public void setConnectBookHouseCount(Integer connectBookHouseCount) {
        this.connectBookHouseCount = connectBookHouseCount;
    }
}
