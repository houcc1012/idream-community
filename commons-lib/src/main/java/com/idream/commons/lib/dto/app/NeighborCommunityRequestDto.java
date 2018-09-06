package com.idream.commons.lib.dto.app;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel(value = "附近社区请求参数(社区位置)")
public class NeighborCommunityRequestDto extends PagesParam {
    @ApiModelProperty(value = "经度")
    @NotNull(message = "longitude不能为空")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    @NotNull(message = "latitude不能为空")
    private BigDecimal latitude;

    @ApiModelProperty(value = "城市编码")
    @NotBlank(message = "cityCode不能为空")
    private String cityCode;

    @ApiModelProperty(value = "当前的大社区id")
    @NotNull(message = "当前的社区id不能为空")
    private Integer regionId;

    @ApiModelProperty(value = "当前的小区id")
    @NotNull(message = "当前的小区id不能为空")
    private Integer communityId;

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

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

}
