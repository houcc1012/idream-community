package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author hejiang
 */
@ApiModel("用户积分明细")
public class UseIntergralDto {

    @ApiModelProperty(value = "主键")
    private int id;

    @ApiModelProperty(value = "使用/获得渠道 : 1签到,2打卡,3抽奖,4兑换")
    private int fromType;

    @ApiModelProperty(value = "类型: 1获取,2使用")
    private int type;

    @ApiModelProperty(value = "记录时间")
    private Date createTime;

    @ApiModelProperty(value = "积分")
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromType() {
        return fromType;
    }

    public void setFromType(int fromType) {
        this.fromType = fromType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
