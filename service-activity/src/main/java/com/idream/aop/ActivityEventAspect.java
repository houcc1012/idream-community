package com.idream.aop;

import com.idream.commons.lib.dto.event.basis.BasisEvent;
import com.idream.commons.lib.enums.EventEnum;
import com.idream.commons.lib.mapper.*;
import com.idream.commons.lib.model.ActivityInfo;
import com.idream.commons.lib.model.CommunityLife;
import com.idream.commons.lib.util.RequestValueUtils;
import com.idream.commons.mvc.annotation.Achievement;
import com.idream.rabbitmq.publisher.ActivityEventPublisher;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.idream.commons.lib.enums.EventEnum.*;

@Component
@Aspect
public class ActivityEventAspect {
    @Autowired
    private ActivityEventPublisher publisher;
    @Autowired
    private EventTypeMapper eventTypeMapper;
    @Autowired
    private ActivityInfoMapper activityInfoMapper;
    @Autowired
    private CommunityLifeMapper communityLifeMapper;
    @Autowired
    private ActivityTaskRecordMapper activityTaskRecordMapper;

    private static final Logger logger = LoggerFactory.getLogger(ActivityEventAspect.class);

    @Pointcut("@annotation(com.idream.commons.mvc.annotation.Achievement)")
    public void achievementPointcut() {
    }

    @AfterReturning(pointcut = "achievementPointcut()", returning = "rtv")
    public void after(JoinPoint jp, Object rtv) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        Achievement annotation = method.getAnnotation(Achievement.class);
        EventType eventType = annotation.eventType();
        Integer userId = RequestValueUtils.getUserId();
        send(eventType, userId, eventType.getCode());
        logger.info("进入切面");
        //发送子事件
        sendChildEvent(rtv, eventType, userId);
    }

    private void sendChildEvent(Object rtv, EventType eventType, Integer userId) {
        Integer eventTypeId = null;

        if (eventType.equals(EventType.ACTIVITY)) {
            ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey((Integer) rtv);
            Integer typeId = activityInfo.getTypeId();
            eventTypeId = eventTypeMapper.selectChildByPidAndValue(eventType.getCode(), typeId);

        }

        if (eventType.equals(EventType.TASK)) {
            Integer activityId = (Integer) RequestValueUtils.getAttributeValue("activityId");

            int count = activityTaskRecordMapper.countByUserIdAndActivityId(userId, activityId);
            if (count == 1) {
                Integer finalUserId = userId;
                eventTypeMapper.selectDistinctChildByActivityId(activityId).forEach(i -> send(eventType, finalUserId, i));
            }
        }
        if (eventType.equals(EventType.ATTENTION)) {
            eventTypeMapper.selectChildByPid(eventType.getCode()).forEach(i -> send(eventType, (Integer) rtv, i));
        }
        if (eventType.equals(EventType.LIKE)) {
            CommunityLife communityLife = communityLifeMapper.selectByPrimaryKey((Integer) rtv);
            eventTypeId = eventTypeMapper.selectChildByPidAndValue(eventType.getCode(), communityLife.getTypeId());
            userId = communityLife.getUserId();
        }


        if (null != eventTypeId) {
            send(eventType, userId, eventTypeId);
        }
    }

    private void send(EventType eventType, Integer userId, Integer eventTypeId) {
        BasisEvent event = new BasisEvent();
        event.setId(eventTypeId);
        event.setType(eventType.name());
        event.setAction(BasisEvent.ACTION_ADD);
        event.setCorrelationId(userId);
        publisher.publish(event);
    }

}
