package com.idream.rabbit.publisher;

import com.idream.commons.lib.dto.event.basis.BasisEvent;
import com.idream.rabbit.channel.AchievementEventProcessor;
import com.idream.rabbit.channel.UserEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@EnableBinding({UserEventProcessor.class, AchievementEventProcessor.class})
@Component
public class UserEventPublisher {
    @Autowired
    private UserEventProcessor eventProcessor;
    @Autowired
    private AchievementEventProcessor achievementEventProcessor;

    public void publish(BasisEvent event) {
        eventProcessor.output().send(MessageBuilder.withPayload(event).build());
    }

    public void achievementPublish(BasisEvent event) {
        achievementEventProcessor.output().send(MessageBuilder.withPayload(event).build());
    }
}
