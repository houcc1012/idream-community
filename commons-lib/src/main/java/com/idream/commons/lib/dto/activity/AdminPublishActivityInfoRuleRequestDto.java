package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: penghekai
 * @Date: 2018/8/22 16:34
 * @Description:
 */
@ApiModel("发布活动报名设置")
public class AdminPublishActivityInfoRuleRequestDto {

    @ApiModelProperty("规则id")
    private Integer infoId;
    @ApiModelProperty("规则名称")
    private String infoName;

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }
}

