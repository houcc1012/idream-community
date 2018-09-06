package com.idream.commons.lib.dto.mail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author hejiang
 */
@ApiModel("发送邮件参数")
public class SendMailParams {

    @ApiModelProperty(value = "邮件接收人地址", required = true)
    private String toMailUser;

    @ApiModelProperty(value = "标题", required = true)
    private String subject;

    @ApiModelProperty(value = "内容", required = true)
    private String text;

    @ApiModelProperty(value = "邮件附件", required = true)
    private List<MailAttachment> mailAttachments;

    public List<MailAttachment> getMailAttachments() {
        return mailAttachments;
    }

    public void setMailAttachments(List<MailAttachment> mailAttachments) {
        this.mailAttachments = mailAttachments;
    }

    public String getToMailUser() {
        return toMailUser;
    }

    public void setToMailUser(String toMailUser) {
        this.toMailUser = toMailUser;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
