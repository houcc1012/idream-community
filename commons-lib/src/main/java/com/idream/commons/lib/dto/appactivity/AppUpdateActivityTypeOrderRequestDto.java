package com.idream.commons.lib.dto.appactivity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: penghekai
 * @Date: 2018/7/18 11:21
 * @Description:
 */
@ApiModel("编辑发现页类型顺序请求参数")
public class AppUpdateActivityTypeOrderRequestDto {

    @ApiModelProperty("位置在前的id")
    private Integer frontTypeId;
    @ApiModelProperty("位置在后的id")
    private Integer backTypeId;

    public Integer getFrontTypeId() {
        return frontTypeId;
    }

    public void setFrontTypeId(Integer frontTypeId) {
        this.frontTypeId = frontTypeId;
    }

    public Integer getBackTypeId() {
        return backTypeId;
    }

    public void setBackTypeId(Integer backTypeId) {
        this.backTypeId = backTypeId;
    }
}

