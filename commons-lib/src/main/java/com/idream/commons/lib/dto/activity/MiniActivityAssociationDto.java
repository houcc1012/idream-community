package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: ZhengGaosheng
 * @date: 2018/7/3 13:49
 * @description:
 */
@ApiModel("活动社群")
public class MiniActivityAssociationDto {

    @ApiModelProperty("活动信息")
    private MiniActivityAssociationActivityInfo activityInfo;

    @ApiModelProperty("打卡信息")
    private MiniActivityAssociationTask task;

    @ApiModelProperty("开奖信息")
    private List<MiniActivityAssociationLottery> lottery;


    public MiniActivityAssociationActivityInfo getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(MiniActivityAssociationActivityInfo activityInfo) {
        this.activityInfo = activityInfo;
    }

    public MiniActivityAssociationTask getTask() {
        return task;
    }

    public void setTask(MiniActivityAssociationTask task) {
        this.task = task;
    }

    public List<MiniActivityAssociationLottery> getLottery() {
        return lottery;
    }

    public void setLottery(List<MiniActivityAssociationLottery> lottery) {
        this.lottery = lottery;
    }
}

