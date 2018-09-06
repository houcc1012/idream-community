package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: ZhengGaosheng
 * @date: 2018/8/6 10:47
 * @description:
 */
@ApiModel("当前活动参加了多少人以及是否参加")
public class ActivityJoinedStatusAndCount {
    @ApiModelProperty("参加状态")
    private Integer joinedStatus;
    @ApiModelProperty("当前参加人数")
    private Integer currentActivityCountPeople;

    public Integer getJoinedStatus() {
        return joinedStatus;
    }

    public void setJoinedStatus(Integer joinedStatus) {
        this.joinedStatus = joinedStatus;
    }

    public Integer getCurrentActivityCountPeople() {
        return currentActivityCountPeople;
    }

    public void setCurrentActivityCountPeople(Integer currentActivityCountPeople) {
        this.currentActivityCountPeople = currentActivityCountPeople;
    }
}

