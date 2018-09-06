package com.idream.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author hejiang
 */
@EnableBinding({SmsProcessor.class})
@Component
public class SmsSendService {

    private static Logger logger = LoggerFactory.getLogger(SmsSendService.class);

    @Autowired
    private SmsProcessor smsProcessor;

    public boolean sendSms(String phone, Byte type, Object data) {
        SmsStreamDto smsStreamDto = new SmsStreamDto();
        smsStreamDto.setType(type);
        smsStreamDto.setPhone(phone);
        smsStreamDto.setSmsSendData(data);
        return smsProcessor.smsOutput().send(MessageBuilder.withPayload(smsStreamDto).build());
    }

    @StreamListener(SmsProcessor.SMS_INPUT)
    public void receiveSms(String message) {
        logger.info("接受短信通道返回信息 ： " + message);
    }

}
