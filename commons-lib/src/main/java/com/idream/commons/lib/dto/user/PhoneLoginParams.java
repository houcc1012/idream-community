package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Pattern;

/**
 * @author hejiang
 */
@ApiModel("app手机登录请求参数")
public class PhoneLoginParams {

    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    @ApiModelProperty(value = "验证码", required = true)
    @Pattern(regexp = "\\d{4}", message = "请输入有效的验证码")
    private String code;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
