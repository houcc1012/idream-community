package com.idream.rabbitmq.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 敏感词
 * @author hejiang
 * @date 2018/8/28
 */
public interface SensitiveWordSink {

    String SENSITIVE_WORD_INPUT = "sensitiveWordInput";

    @Input(SENSITIVE_WORD_INPUT)
    SubscribableChannel sensitiveWordInput();
}
