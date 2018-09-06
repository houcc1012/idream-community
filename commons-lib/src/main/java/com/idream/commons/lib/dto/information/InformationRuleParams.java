package com.idream.commons.lib.dto.information;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author charles
 */
@ApiModel("规则信息")
public class InformationRuleParams {
    @ApiModelProperty("规则id")
    private Integer infoId;
    @ApiModelProperty("规则code")
    private String infoCode;
    @ApiModelProperty("规则名称")
    private String infoName;
    @ApiModelProperty("详情")
    private String detail;

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }
}
