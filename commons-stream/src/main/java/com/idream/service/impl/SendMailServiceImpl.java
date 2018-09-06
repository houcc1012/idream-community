package com.idream.service.impl;

import com.idream.commons.lib.dto.mail.MailAttachment;
import com.idream.commons.lib.dto.mail.SendMailParams;
import com.idream.service.SendMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author hejiang
 */
@Service
public class SendMailServiceImpl implements SendMailService {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void sendSimpleMail(SendMailParams params) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(params.getToMailUser());
        message.setSubject(params.getSubject());
        message.setText(params.getText());
        javaMailSender.send(message);
    }

    @Override
    public void sendHtmlMail(SendMailParams params) {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo(params.getToMailUser());
            helper.setSubject(params.getSubject());
            helper.setText(params.getText(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }

    @Override
    public void sendAttachmentsMail(SendMailParams params) {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo(params.getToMailUser());
            helper.setSubject(params.getSubject());
            helper.setText(params.getText());
            for (MailAttachment mailAttachment : params.getMailAttachments()) {
                FileSystemResource file = new FileSystemResource(new File(mailAttachment.getFilePath()));
                helper.addAttachment(mailAttachment.getFileSendName(), file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }
}
