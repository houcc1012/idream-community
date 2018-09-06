package com.idream.commons.lib.dto.quartz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("操作Quartz定时任务请求参数")
public class OperateQuartzJobParams {

    @ApiModelProperty(value = "job 类名, 建议传全类名,非全类名情况或默认是 com.idream.job.impl 包下", required = true)
    private String jobClassName;

    @ApiModelProperty(value = "job 分组名称, 可不传, 不传默认job类名后加 _group", required = true)
    private String jobGroupName;

    @ApiModelProperty(value = "job执行时间 cron 表达式", required = true)
    private String cronExpression;

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}
