package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.model.RegionGroupInfo;
import com.idream.commons.lib.model.RegionInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Auther: penghekai
 * @Date: 2018/6/30 17:06
 * @Description:
 */
@ApiModel("大社区和包含的所有小社区")
public class RegionInfoExtention {

    @ApiModelProperty(value = "主键")
    private Integer regionId;
    @ApiModelProperty(value = "社区名")
    private String regionName;
    @ApiModelProperty("包含的所有小社区")
    private List<RegionGroupInfoExtention> regionGroupInfoList;

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

    public List<RegionGroupInfoExtention> getRegionGroupInfoList() {
        return regionGroupInfoList;
    }

    public void setRegionGroupInfoList(List<RegionGroupInfoExtention> regionGroupInfoList) {
        this.regionGroupInfoList = regionGroupInfoList;
    }
}

