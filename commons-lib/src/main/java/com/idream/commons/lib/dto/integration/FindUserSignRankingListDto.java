package com.idream.commons.lib.dto.integration;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author hejiang
 */
@ApiModel("查询用户签到积分排行榜列表返回参数")
public class FindUserSignRankingListDto {

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "用户Id")
    private int userId;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "累计签到天数")
    private int totalSignDay;

    @ApiModelProperty(value = "最高持续签到天数")
    private int continuedSignDay;

    @ApiModelProperty(value = "累计签到积分")
    private int totalScore;

    @ApiModelProperty(value = "最后一次签到日期")
    private Date lastSignDay;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getTotalSignDay() {
        return totalSignDay;
    }

    public void setTotalSignDay(int totalSignDay) {
        this.totalSignDay = totalSignDay;
    }

    public int getContinuedSignDay() {
        return continuedSignDay;
    }

    public void setContinuedSignDay(int continuedSignDay) {
        this.continuedSignDay = continuedSignDay;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public Date getLastSignDay() {
        return lastSignDay;
    }

    public void setLastSignDay(Date lastSignDay) {
        this.lastSignDay = lastSignDay;
    }
}
