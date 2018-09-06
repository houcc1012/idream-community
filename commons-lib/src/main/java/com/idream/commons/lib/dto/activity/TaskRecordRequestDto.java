package com.idream.commons.lib.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "设置打卡积分")
public class TaskRecordRequestDto {

    @ApiModelProperty(value = "打卡积分")
    private Integer taskRecord;

    public Integer getTaskRecord() {
        return taskRecord;
    }

    public void setTaskRecord(Integer taskRecord) {
        this.taskRecord = taskRecord;
    }
}
