package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Description :
 * Created by DELL2018-003 on 2018/4/12.
 */
@ApiModel("我的兑奖记录")
public class MyAwardRecordDto {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "poolId")
    private Integer poolId;

    @ApiModelProperty(value = "券码")
    private String couponCode;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "奖品名称")
    private String awardName;

    @ApiModelProperty(value = "兑换状态,1未兑换,2兑换成功,3过期")
    private Integer status;

    @ApiModelProperty(value = "抽奖时间")
    private Date createTime;

    @ApiModelProperty(value = "兑奖时间")
    private Date updateTime;

    @ApiModelProperty(value = "兑奖责任人")
    private Integer operateUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode == null ? null : couponCode.trim();
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName == null ? null : awardName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPoolId() {
        return poolId;
    }

    public void setPoolId(Integer poolId) {
        this.poolId = poolId;
    }

    public Integer getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Integer operateUserId) {
        this.operateUserId = operateUserId;
    }
}
