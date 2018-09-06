package com.idream.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 网易消息抄送mq发送服务
 *
 * @author hejiang
 */
@EnableBinding({WangyiIMCallBackProcessor.class})
@Component
public class WangyiIMCallBackSendService {

    private Logger logger = LoggerFactory.getLogger(WangyiIMCallBackSendService.class);

    @Autowired
    private WangyiIMCallBackProcessor wangyiIMCallBackProcessor;

    public boolean sendImNoticeCallbackMessage(String msg) {
        return wangyiIMCallBackProcessor.noticeCallbackOutput().send(MessageBuilder.withPayload(msg).build());
    }
}
