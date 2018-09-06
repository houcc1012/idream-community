package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "区域社区返回dto")
public class CommunityRegionResponseDto {
    @ApiModelProperty(value = "关联表id")
    private Integer id;

    @ApiModelProperty(value = "区域id")
    private Integer regionId;

    @ApiModelProperty(value = "区域name")
    private String regionName;

    @ApiModelProperty(value = "社区id")
    private Integer communityId;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
