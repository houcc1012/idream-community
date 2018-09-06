package com.idream.feign.impl;

import com.idream.commons.lib.dto.mail.SendMailParams;
import com.idream.feign.FeignSendMailService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: ZhengGaosheng
 * @date: 2018/6/11 19:16
 * @description:
 */
@Component
public class FeignSendMailServiceImpl implements FeignSendMailService {
    @Override
    public void sendSimpleMail(@RequestBody SendMailParams params) {
        return;
    }
}

