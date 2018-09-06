package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "管理端活动发布接收实体")
public class AdminActivityPublishDto extends PublishDto {

    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "省编码")
    private String provinceCode;
    @ApiModelProperty(value = "区域")
    private String district;
    @ApiModelProperty(value = "区域编码")
    private String districtCode;
    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;
    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
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
}
