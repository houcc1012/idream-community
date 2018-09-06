package com.idream.rabbit.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 敏感词
 * @author hejiang
 * @date 2018/8/28
 */
public interface SensitiveWordSource {

    String SENSITIVE_WORD_INPUT = "sensitiveWordInput";
//
//    String SENSITIVE_WORD_OUTPUT = "sensitiveWordOutput";

//    @Input(SENSITIVE_WORD_INPUT)
//    SubscribableChannel sensitiveWordInput();

    @Output(SENSITIVE_WORD_INPUT)
    MessageChannel sensitiveWordOutput();

}
