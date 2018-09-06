package com.idream.rabbit.publisher;

import com.idream.rabbit.channel.SensitiveWordSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 敏感字队列处理
 * @author hejiang
 * @date 2018/8/28
 */

@EnableBinding({SensitiveWordSource.class})
@Component
public class SensitiveWordPublisher {

    @Autowired
    private SensitiveWordSource sensitiveWordSource;

    public void publish(String str) {
        sensitiveWordSource.sensitiveWordOutput().send(MessageBuilder.withPayload(str).build());
    }
}
