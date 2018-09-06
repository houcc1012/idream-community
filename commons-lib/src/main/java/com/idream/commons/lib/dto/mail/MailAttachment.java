package com.idream.commons.lib.dto.mail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hejiang
 */
@ApiModel("邮件附件内容")
public class MailAttachment {

    @ApiModelProperty("附件文件路径")
    private String filePath;

    @ApiModelProperty("附件发送名称")
    private String fileSendName;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSendName() {
        return fileSendName;
    }

    public void setFileSendName(String fileSendName) {
        this.fileSendName = fileSendName;
    }
}
