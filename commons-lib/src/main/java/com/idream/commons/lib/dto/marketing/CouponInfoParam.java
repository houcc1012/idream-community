package com.idream.commons.lib.dto.marketing;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/12.
 */
@ApiModel("中奖卡券查询参数")
public class CouponInfoParam extends PagesParam {

    @ApiModelProperty(value = "券码")
    private String couponCode;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "来源/（1.抽奖，2.积分）")
    @NotNull(message = "查询来源不能为空")
    private Integer fromType;

    @ApiModelProperty(value = "状态/（4.所有，1.未兑奖，2.兑奖成功，3.已过期）")
    @NotNull(message = "状态不能为空")
    private Integer status;

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
