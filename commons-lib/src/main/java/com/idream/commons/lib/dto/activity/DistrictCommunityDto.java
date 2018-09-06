package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "区域社区实体类")
public class DistrictCommunityDto {

    @ApiModelProperty(value = "区域名")
    private String district;
    @ApiModelProperty(value = "社区名")
    private String community;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

}
