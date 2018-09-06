package com.idream.commons.lib.dto.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Pattern;

/**
 * @Description :
 * @Created by xiaogang on 2018/7/21.
 */
@ApiModel("抽奖奖品规则")
public class IntegrationRule {

    @ApiModelProperty("满多少")
    private String full;

    @ApiModelProperty("减多少")
    private String cut;

    @ApiModelProperty("多少折")
    private String discount;

    @ApiModelProperty("代金金额")
    private String cash;

    @ApiModelProperty("积分")
    private String score;

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getCut() {
        return cut;
    }

    public void setCut(String cut) {
        this.cut = cut;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
