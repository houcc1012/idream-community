package com.idream.commons.lib.dto.admin;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: jeffery
 * @Date: 2018/8/6 13:58
 */
@ApiModel(value = "其他推广 请求参数")
public class OtherPromotionParams extends PagesParam {
    @ApiModelProperty(value = "省code")
    private String provinceCode;

    @ApiModelProperty(value = "城市code")
    private String cityCode;

    @ApiModelProperty(value = "地推名称")
    private String teamName;

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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
