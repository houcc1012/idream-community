package com.idream.commons.lib.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles
 */
@ApiModel("发现页实体")
public class AppDiscoveryDto {
    @ApiModelProperty("图片")
    private String image;
    @ApiModelProperty("业务类型,1活动,2活动精彩,3生活动态")
    private Integer businessType;
    @ApiModelProperty("业务id")
    private Integer businessId;
    @ApiModelProperty("活动类型")
    private Integer typeId;
    @ApiModelProperty("标题")
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
