package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

import java.util.Date;

@ApiModel("卡券记录")
public class CouponRecordDto {

    @ApiParam(value = "用户昵称")
    private String nickName;

    @ApiParam(value = "用户手机号")
    private String phone;

    @ApiParam(value = "来源（1:抽奖，2:兑奖，3:开奖）")
    private Byte fromType;

    @ApiParam(value = "奖品名")
    private String awardName;

    @ApiParam(value = "中奖时间")
    private Date createTime;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Byte getFromType() {
        return fromType;
    }

    public void setFromType(Byte fromType) {
        this.fromType = fromType;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
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
}
