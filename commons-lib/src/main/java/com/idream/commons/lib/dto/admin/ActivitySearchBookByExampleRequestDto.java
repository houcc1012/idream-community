package com.idream.commons.lib.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: penghekai
 * @Date: 2018/8/3 13:49
 * @Description:
 */
@ApiModel("书屋输入式查询请求参数")
public class ActivitySearchBookByExampleRequestDto {

    @ApiModelProperty(value = "省份编码")
    private String provinceCode;
    @ApiModelProperty(value = "城市编码")
    private String cityCode;
    @ApiModelProperty(value = "地区编码")
    private String districtCode;
    @ApiModelProperty(value = "社区名称")
    private String regionName;
    @ApiModelProperty(value = "书屋名称")
    private String bookName;

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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}

