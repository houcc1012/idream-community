package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
/**
 * 书屋名称  返回参数
 * @author zhanfeifei
 * @param
 * @return
 * 2018年4月12日
 *
 */
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "书屋名称 返回参数")
public class BookHouseRequestDto {

    @ApiModelProperty(value = "省")
    private String privince;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String district;

    public String getPrivince() {
        return privince;
    }

    public void setPrivince(String privince) {
        this.privince = privince;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


}
