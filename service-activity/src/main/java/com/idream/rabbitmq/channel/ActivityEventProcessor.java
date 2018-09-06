package com.idream.rabbitmq.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author charles
 */
public interface ActivityEventProcessor {
    String EVENT_INPUT = "eventInput";
    String EVENT_OUTPUT = "eventOutput";

    @Output(EVENT_OUTPUT)
    MessageChannel output();
//    @Input(EVENT_INPUT)
//    SubscribableChannel input();
}
