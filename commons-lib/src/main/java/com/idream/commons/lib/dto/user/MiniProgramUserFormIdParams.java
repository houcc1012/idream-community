package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("小程序formId收集请求记录")
public class MiniProgramUserFormIdParams {

    @ApiModelProperty(value = "formId", required = true)
    private String formId;

    @ApiModelProperty(value = "失效时间, 格式: yyyy-MM-dd HH:mm:ss", required = true)
    private String expireTime;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
}
