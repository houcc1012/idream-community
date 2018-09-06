package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CouponCollection {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "优惠券id")
    private Integer couponId;

    @ApiModelProperty(value = "券码")
    private String couponCode;

    @ApiModelProperty(value = "收集信息规则ID")
    private Integer infoId;

    @ApiModelProperty(value = "收集信息规则code")
    private String infoCode;

    @ApiModelProperty(value = "收集信息规则名称")
    private String infoName;

    @ApiModelProperty(value = "收集信息排序")
    private Byte infoSort;

    @ApiModelProperty(value = "内容")
    private String detail;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode == null ? null : couponCode.trim();
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode == null ? null : infoCode.trim();
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName == null ? null : infoName.trim();
    }

    public Byte getInfoSort() {
        return infoSort;
    }

    public void setInfoSort(Byte infoSort) {
        this.infoSort = infoSort;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}