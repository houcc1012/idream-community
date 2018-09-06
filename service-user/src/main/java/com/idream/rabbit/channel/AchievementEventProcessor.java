package com.idream.rabbit.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface AchievementEventProcessor {
    String ACHIEVEMENT_EVENT_INPUT = "achievementEventInput";
    String ACHIEVEMENT_EVENT_OUTPUT = "achievementEventOutput";

    @Output(ACHIEVEMENT_EVENT_OUTPUT)
    MessageChannel output();

    @Input(ACHIEVEMENT_EVENT_INPUT)
    SubscribableChannel input();
}
