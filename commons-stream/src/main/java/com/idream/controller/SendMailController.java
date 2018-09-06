package com.idream.controller;

import com.idream.commons.lib.dto.mail.SendMailParams;
import com.idream.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hejiang
 */
@RestController
public class SendMailController {

    @Autowired
    private SendMailService sendMailService;

    @RequestMapping(value = "/send/simple/mail", method = RequestMethod.POST)
    public void sendSimpleMail(@RequestBody SendMailParams params) {
        sendMailService.sendSimpleMail(params);
    }

    @RequestMapping(value = "/send/html/mail", method = RequestMethod.POST)
    public void sendHtmlMail(@RequestBody SendMailParams params) {
        sendMailService.sendHtmlMail(params);
    }

    @RequestMapping(value = "/send/attachment/mail", method = RequestMethod.POST)
    public void sendAttachmentsMail(@RequestBody SendMailParams params) {
        sendMailService.sendAttachmentsMail(params);
    }
}
