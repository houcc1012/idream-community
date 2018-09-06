package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 奖券收集的用户信息表
 */
@ApiModel("奖券收集的用户信息表")
public class CouponCollectionParams {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "主键ID")
    private Integer couponId;

    @ApiModelProperty(value = "内容")
    @NotBlank()
    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }
}