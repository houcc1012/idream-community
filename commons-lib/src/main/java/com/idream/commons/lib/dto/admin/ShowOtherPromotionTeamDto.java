package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/8/7 13:30
 */
@ApiModel(value = "其他推广 编辑修改回显返回参数")
public class ShowOtherPromotionTeamDto {
    @ApiModelProperty(value = "推广id")
    private Integer id;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "省code")
    private String provinceCode;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "市code")
    private String cityCode;

    @ApiModelProperty(value = "推广名称")
    private String teamName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
