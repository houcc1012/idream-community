package com.idream.commons.lib.dto.integration;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("老积分配置表")
public class OldIntegrationConfigDto {
    /**
     * 签到,第1天分数
     */
    @ApiModelProperty(value = "签到,第1天分数")
    private Integer one;

    /**
     * 签到,第2天分数
     */
    @ApiModelProperty(value = "签到,第2天分数")
    private Integer two;

    /**
     * 签到,第3天分数
     */
    @ApiModelProperty(value = "签到,第3天分数")
    private Integer three;

    /**
     * 签到,第4天分数
     */
    @ApiModelProperty(value = "签到,第4天分数")
    private Integer four;

    /**
     * 签到,第五天分数
     */
    @ApiModelProperty(value = "签到,第五天分数")
    private Integer five;

    /**
     * 签到,第六天分数
     */
    @ApiModelProperty(value = "签到,第六天分数")
    private Integer six;

    /**
     * 签到,第七天及以上
     */
    @ApiModelProperty(value = "签到,第七天及以上")
    private Integer seven;

    /**
     * 7天抽奖,1开启,0关闭
     */
    @ApiModelProperty(value = "7天抽奖,1开启,0关闭")
    private Integer lotteryEnable;

    /**
     * 打卡积分
     */
    @ApiModelProperty(value = "打卡积分")
    private Integer taskScore;

    public void setOne(Integer one) {
        this.one = one;
    }

    public void setTwo(Integer two) {
        this.two = two;
    }

    public void setThree(Integer three) {
        this.three = three;
    }

    public void setFour(Integer four) {
        this.four = four;
    }

    public void setFive(Integer five) {
        this.five = five;
    }

    public void setSix(Integer six) {
        this.six = six;
    }

    public void setSeven(Integer seven) {
        this.seven = seven;
    }

    public void setLotteryEnable(Integer lotteryEnable) {
        this.lotteryEnable = lotteryEnable;
    }

    public void setTaskScore(Integer taskScore) {
        this.taskScore = taskScore;
    }

    public Integer getOne() {
        return one;
    }

    public Integer getTwo() {
        return two;
    }

    public Integer getThree() {
        return three;
    }

    public Integer getFour() {
        return four;
    }

    public Integer getFive() {
        return five;
    }

    public Integer getSix() {
        return six;
    }

    public Integer getSeven() {
        return seven;
    }

    public Integer getLotteryEnable() {
        return lotteryEnable;
    }

    public Integer getTaskScore() {
        return taskScore;
    }
}
