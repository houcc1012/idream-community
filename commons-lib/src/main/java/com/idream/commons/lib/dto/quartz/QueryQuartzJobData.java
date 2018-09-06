package com.idream.commons.lib.dto.quartz;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("定时任务查询返回对象")
public class QueryQuartzJobData {

    @ApiModelProperty("任务名称")
    private String jobName;

    @ApiModelProperty("任务分组")
    private String jobGroup;

    @ApiModelProperty("任务类名")
    private String jobClassName;

    @ApiModelProperty("触发器名称")
    private String triggerName;

    @ApiModelProperty("触发器分组")
    private String triggerGroup;

    @ApiModelProperty("cron 表达式")
    private String cronExpression;

    @ApiModelProperty("任务执行地区信息")
    private String timeZoneId;

    @ApiModelProperty(value = "状态：BLOCKED-运行中 ；COMPLETE-已完成 ；ERROR-异常；NONE-不存在；NORMAL-正常；PAUSED-暂停")
    private String triggerState;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getTriggerState() {
        return triggerState;
    }

    public void setTriggerState(String triggerState) {
        this.triggerState = triggerState;
    }
}
