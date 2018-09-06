package com.idream.service;

import com.idream.commons.lib.dto.mail.SendMailParams;

/**
 * @author hejiang
 */
public interface SendMailService {

    /**
     * @Author: hejiang
     * @Description: 发送普通邮件
     * @Date: 10:08 2018/5/28
     */
    void sendSimpleMail(SendMailParams params);

    /**
     * @Author: hejiang
     * @Description: 发送html邮件
     * @Date: 10:08 2018/5/28
     */
    void sendHtmlMail(SendMailParams params);

    /**
     * @Author: hejiang
     * @Description: 发送带附件的邮件
     * @Date: 10:08 2018/5/28
     */
    void sendAttachmentsMail(SendMailParams params);
}
