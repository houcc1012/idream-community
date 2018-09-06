package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: penghekai
 * @Date: 2018/8/29 14:14
 * @Description:
 */
public class ActivityAdminCustomerInfoDto {

    @ApiModelProperty("规则id")
    private Integer infoId;
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

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}

