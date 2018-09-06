package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("积分抽奖响应参数")
public class IntegrationDrawDto {

    @ApiModelProperty(value = "奖品名称")
    private String awardName;

    @ApiModelProperty(value = "奖品图片")
    private String awardImage;

    @ApiModelProperty(value = "奖券类型:1实物；2满减；3折扣；4代金; 5积分")
    private Byte type;

    @ApiModelProperty(value = "奖品值,json字符串.如7折折扣券 {'discount':'7'}...")
    private String integrationValue;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getAwardImage() {
        return awardImage;
    }

    public void setAwardImage(String awardImage) {
        this.awardImage = awardImage;
    }

    public String getIntegrationValue() {
        return integrationValue;
    }

    public void setIntegrationValue(String integrationValue) {
        this.integrationValue = integrationValue;
    }
}
