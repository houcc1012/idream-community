package com.idream.feign;

import com.idream.commons.lib.dto.mail.SendMailParams;
import com.idream.feign.impl.FeignSendMailServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: ZhengGaosheng
 * @Date: 2018/6/11 19:14
 * @Description: 发送邮件
 */
@FeignClient(value = "stream-server", fallback = FeignSendMailServiceImpl.class)
public interface FeignSendMailService {

    @RequestMapping(value = "/send/simple/mail", method = RequestMethod.POST, consumes = "application/json")
    void sendSimpleMail(@RequestBody SendMailParams params);

}
