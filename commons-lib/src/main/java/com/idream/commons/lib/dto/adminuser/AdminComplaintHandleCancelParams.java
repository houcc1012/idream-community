package com.idream.commons.lib.dto.adminuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel("取消处理接收参数")
public class AdminComplaintHandleCancelParams {
    @ApiModelProperty("处理id")
    @NotNull(message = "处理编号不能为空")
    private Integer handleId;

    public void setHandleId(Integer handleId) {
        this.handleId = handleId;
    }

    public Integer getHandleId() {
        return handleId;
    }
}
