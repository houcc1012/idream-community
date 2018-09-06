package com.idream.commons.lib.dto.user;

import com.idream.commons.lib.annotation.SensitiveWordVaild;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author hejiang
 */
@ApiModel("app游客注册请求参数")
public class AppUserRegisterParams {

    @ApiModelProperty("机器码")
    @NotBlank(message = "machineCode不能为空")
    @Length(max = 64, message = "machineCode最大不能超过64位")
    private String machineCode;

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }
}
