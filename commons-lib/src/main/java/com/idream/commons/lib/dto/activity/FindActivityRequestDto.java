package com.idream.commons.lib.dto.activity;

import com.idream.commons.lib.dto.PagesParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author penghekai
 */
@ApiModel(value = "条件查询活动请求参数")
public class FindActivityRequestDto extends PagesParam {

    @ApiModelProperty(value = "活动标题")
    private String title;
    @ApiModelProperty(value = "活动类型")
    private Integer typeId;
    @ApiModelProperty(value = "省级编码")
    private String provinceCode;
    @ApiModelProperty(value = "城市编码")
    private String cityCode;
    @ApiModelProperty(value = "区域编码")
    private String adCode;
    @ApiModelProperty(value = "社区名字")
    private String communityName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

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

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

}
