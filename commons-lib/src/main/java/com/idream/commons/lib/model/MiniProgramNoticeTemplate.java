package com.idream.commons.lib.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("mini_program_notice_template")
public class MiniProgramNoticeTemplate {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "模板消息ID")
    private String templateId;

    @ApiModelProperty(value = "模板标题")
    private String templateTitle;

    @ApiModelProperty(value = "模板内容")
    private String templateData;

    @ApiModelProperty(value = "跳转url")
    private String goToUrl;

    @ApiModelProperty(value = "描述 模板内容的描述 如 keyword1:活动主题 ....")
    private String templateDesc;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

    public String getTemplateTitle() {
        return templateTitle;
    }

    public void setTemplateTitle(String templateTitle) {
        this.templateTitle = templateTitle == null ? null : templateTitle.trim();
    }

    public String getTemplateData() {
        return templateData;
    }

    public void setTemplateData(String templateData) {
        this.templateData = templateData == null ? null : templateData.trim();
    }

    public String getGoToUrl() {
        return goToUrl;
    }

    public void setGoToUrl(String goToUrl) {
        this.goToUrl = goToUrl == null ? null : goToUrl.trim();
    }

    public String getTemplateDesc() {
        return templateDesc;
    }

    public void setTemplateDesc(String templateDesc) {
        this.templateDesc = templateDesc == null ? null : templateDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}