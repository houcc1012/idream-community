package com.idream.aop;

import com.idream.commons.lib.dto.event.basis.BasisEvent;
import com.idream.commons.lib.enums.EventEnum;
import com.idream.commons.lib.util.RequestValueUtils;
import com.idream.commons.mvc.annotation.Achievement;
import com.idream.rabbit.publisher.UserEventPublisher;
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

@Component
@Aspect
public class UserEventAspect {
    @Autowired
    private UserEventPublisher publisher;
    private static final Logger logger = LoggerFactory.getLogger(UserEventAspect.class);

    @Pointcut("@annotation(com.idream.commons.mvc.annotation.Achievement)")
    public void achievementPointcut() {
    }

    @AfterReturning(pointcut = "achievementPointcut()", returning = "rtv")
    public void after(JoinPoint jp, Object rtv) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        Achievement annotation = method.getAnnotation(Achievement.class);
        EventEnum.EventType eventType = annotation.eventType();

        Integer userId = RequestValueUtils.getUserId();

        BasisEvent event = new BasisEvent();
        event.setId(eventType.getCode());
        event.setType(eventType.name());
        event.setAction(BasisEvent.ACTION_ADD);
        event.setCorrelationId(userId);
        if (rtv instanceof Integer) {
            event.setOrganizationId((Integer) rtv);
        }
        publisher.publish(event);
    }
}
