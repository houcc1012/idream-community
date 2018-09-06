package com.idream.rabbit;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 短信 sc Stream 自定义通道
 *
 * @author hejiang
 */
public interface SmsProcessor {

    String SMS_INPUT = "smsInput";

    String SMS_OUTPUT = "smsOutput";

    @Input(SMS_INPUT)
    SubscribableChannel smsInput();

    @Output(SMS_OUTPUT)
    MessageChannel smsOutput();

}
