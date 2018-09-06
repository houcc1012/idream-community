package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author DELL2018-01
 */
@ApiModel(value = "用户注册-启动小程序请求参数")
public class UserRegisterParams {
    @ApiModelProperty(value = "encryptedData")
    private String encryptedData;
    @ApiModelProperty(value = "iv")
    private String iv;
    @ApiModelProperty(value = "code")
    private String code;

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
