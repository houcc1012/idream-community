package com.idream.commons.lib.dto.marketing;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;


@ApiModel("卡券查询参数")
public class MyCouponInfoParam extends PagesParam {

    @ApiModelProperty(value = "用户id")
    private Integer authUserId;

    @ApiModelProperty(value = "查询类型（0所有,1通用，2区域）")
    @NotNull(message = "查询类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "状态/（不传为所有，1.上架，2.下架）")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(Integer authUserId) {
        this.authUserId = authUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
