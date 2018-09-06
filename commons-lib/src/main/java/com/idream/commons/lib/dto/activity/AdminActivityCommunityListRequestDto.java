package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: penghekai
 * @Date: 2018/6/21 10:56
 * @Description:
 */
@ApiModel("创建活动选择社区请求参数")
public class AdminActivityCommunityListRequestDto {

    @ApiModelProperty(value = "省编码")
    private String provinceCode;
    @ApiModelProperty(value = "市编码")
    private String cityCode;
    @ApiModelProperty(value = "区编码")
    private String districtCode;
    @ApiModelProperty("高级社区名")
    private String regionName;

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}

