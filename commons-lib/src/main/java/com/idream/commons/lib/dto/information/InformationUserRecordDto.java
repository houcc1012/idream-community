package com.idream.commons.lib.dto.information;

import io.swagger.annotations.ApiModel;

@ApiModel("用户录入信息回显")
public class InformationUserRecordDto {
    private String infoCode;
    private String detail;


    public String getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
