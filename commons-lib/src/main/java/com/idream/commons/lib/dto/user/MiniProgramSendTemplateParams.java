package com.idream.commons.lib.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author houcc
 */
@ApiModel("小程序发送模板消息")
public class MiniProgramSendTemplateParams {

    @ApiModelProperty(value = "活动ID", required = true)
    private Integer activityId;

    @ApiModelProperty(value = "模板消息ID", required = true)
    private String templateId;

    @ApiModelProperty(value = "跳转url", required = true)
    private String page;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
