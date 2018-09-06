package com.idream.rabbit.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author charles
 */
public interface UserEventProcessor {
    String EVENT_INPUT = "eventInput";
    String EVENT_OUTPUT = "eventOutput";

    @Output(EVENT_OUTPUT)
    MessageChannel output();

    @Input(EVENT_INPUT)
    SubscribableChannel input();
}
