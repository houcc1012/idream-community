package com.idream;

import com.idream.commons.lib.dto.event.basis.BasisEvent;
import com.idream.commons.lib.enums.EventEnum;
import com.idream.rabbit.publisher.UserEventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventAchievementTest {
    @Autowired
    private UserEventPublisher publisher;

    @Test
    public void authCommunityTest() {
        for (int i = 0; i < 15; i++) {
            BasisEvent event = new BasisEvent();
            event.setId(EventEnum.EventType.COMMUNITY_AUTH.getCode());
            event.setCorrelationId(150);
            event.setAction(BasisEvent.ACTION_ADD);
            event.setType(EventEnum.EventType.COMMUNITY_AUTH.name());
            publisher.publish(event);
        }
    }

    @Test
    public void fansTest() {
        for (int i = 0; i < 20; i++) {
            BasisEvent event = new BasisEvent();
            event.setId(EventEnum.EventType.ATTENTION.getCode());
            event.setCorrelationId(150);
            event.setOrganizationId(151);
            event.setAction(BasisEvent.ACTION_ADD);
            event.setType(EventEnum.EventType.ATTENTION.name());
            publisher.publish(event);
        }
    }

    @Test
    public void activityTest() {
        for (int i = 0; i < 20; i++) {
            BasisEvent event = new BasisEvent();
            event.setId(EventEnum.EventType.ACTIVITY.getCode());
            event.setCorrelationId(150);
            event.setOrganizationId(151);
            event.setAction(BasisEvent.ACTION_ADD);
            event.setType(EventEnum.EventType.ACTIVITY.name());
            publisher.publish(event);
        }
    }

    @Test
    public void activity306Test() {
        for (int i = 0; i < 20; i++) {
            BasisEvent event = new BasisEvent();
            event.setId(306);
            event.setCorrelationId(180);
            event.setOrganizationId(151);
            event.setAction(BasisEvent.ACTION_ADD);
            event.setType(EventEnum.EventType.ACTIVITY.name());
            publisher.publish(event);
        }
    }

    @Test
    public void dy602Test() {
        for (int i = 0; i < 20; i++) {
            BasisEvent event = new BasisEvent();
            event.setId(602);
            event.setCorrelationId(180);
            event.setOrganizationId(151);
            event.setAction(BasisEvent.ACTION_ADD);
            event.setType(EventEnum.EventType.ACTIVITY.name());
            publisher.publish(event);
        }
    }
}
