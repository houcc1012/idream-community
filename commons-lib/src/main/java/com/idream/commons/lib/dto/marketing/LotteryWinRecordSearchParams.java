package com.idream.commons.lib.dto.marketing;

import com.idream.commons.lib.dto.PagesParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/13.
 */
@ApiModel(value = "开奖记录搜索条件")
public class LotteryWinRecordSearchParams extends PagesParam {

    @ApiModelProperty(value = "开奖Id")
    private Integer lotteryId;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 默认1未兑奖,2已兑奖
     */
    @ApiModelProperty(value = "兑奖状态：1未兑奖,2已兑奖")
    private Integer status;

    /**
     * 券码
     */
    @ApiModelProperty(value = "券码")
    private String couponCode;

    public Integer getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Integer lotteryId) {
        this.lotteryId = lotteryId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
