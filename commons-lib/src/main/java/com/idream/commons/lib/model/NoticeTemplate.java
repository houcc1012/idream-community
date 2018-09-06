package com.idream.commons.lib.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 消息通知模板
 */
@ApiModel("消息通知模板")
public class NoticeTemplate {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 模板类型 1-站内信 ; 2-app推送
     */
    @ApiModelProperty(value = "模板类型 1-站内信 ; 2-app推送")
    private Byte templateType;

    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型")
    private Byte businessType;

    /**
     * 模板状态 1-有效;2-失效;3-草稿
     */
    @ApiModelProperty(value = "模板状态 1-有效;2-失效;3-草稿")
    private Byte templateStatus;

    /**
     * 跳转地址
     */
    @ApiModelProperty(value = "跳转地址")
    private String goToUrl;

    /**
     * 生成时间
     */
    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Byte templateType) {
        this.templateType = templateType;
    }

    public Byte getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Byte businessType) {
        this.businessType = businessType;
    }

    public Byte getTemplateStatus() {
        return templateStatus;
    }

    public void setTemplateStatus(Byte templateStatus) {
        this.templateStatus = templateStatus;
    }

    public String getGoToUrl() {
        return goToUrl;
    }

    public void setGoToUrl(String goToUrl) {
        this.goToUrl = goToUrl == null ? null : goToUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}