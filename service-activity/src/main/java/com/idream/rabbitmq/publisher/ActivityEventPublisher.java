package com.idream.rabbitmq.publisher;

import com.idream.commons.lib.dto.event.basis.BasisEvent;
import com.idream.rabbitmq.channel.ActivityEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@EnableBinding({ActivityEventProcessor.class})
@Component
public class ActivityEventPublisher {
    @Autowired
    private ActivityEventProcessor activityEventProcessor;

    public void publish(BasisEvent event) {
        activityEventProcessor.output().send(MessageBuilder.withPayload(event).build());
    }
}
