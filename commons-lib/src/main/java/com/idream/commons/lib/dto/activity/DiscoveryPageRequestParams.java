package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import javax.validation.constraints.NotBlank;

/**
 * @author: ZhengGaosheng
 * @date: 2018/7/18 15:58
 * @description:
 */
@ApiModel("小程序发现页参数请求模型")
public class DiscoveryPageRequestParams extends PagesParam {

    @ApiModelProperty("城市编码")
    @NotBlank(message = "城市编码不能为空")
    private String cityCode;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty("类型id")
    private String typeId;

    @ApiModelProperty(value = "用户Id", hidden = true)
    private int authUserId;

    @ApiModelProperty("'排序规则,默认是1(距离)")
    private Integer sortType = 1;


    public int getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(int authUserId) {
        this.authUserId = authUserId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getSortType() {
        return sortType;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }
}

