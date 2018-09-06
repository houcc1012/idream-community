package com.idream.rabbit.handler;

import com.idream.commons.lib.dto.event.basis.BasisEvent;
import com.idream.commons.lib.enums.EventEnum;
import com.idream.commons.lib.mapper.AchievementUserMapper;
import com.idream.commons.lib.mapper.AchievementUserRecordMapper;
import com.idream.commons.lib.mapper.EventInfoMapper;
import com.idream.commons.lib.model.AchievementUser;
import com.idream.commons.lib.model.AchievementUserRecord;
import com.idream.commons.lib.model.EventInfo;
import com.idream.commons.lib.util.DateUtils;
import com.idream.rabbit.channel.AchievementEventProcessor;
import com.idream.rabbit.channel.UserEventProcessor;
import com.idream.rabbit.publisher.UserEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author charles
 */
@Component
public class AchievementEventHandler {
    private Logger logger = LoggerFactory.getLogger(AchievementEventHandler.class);
    @Autowired
    private AchievementUserRecordMapper achievementUserRecordMapper;
    @Autowired
    private EventInfoMapper eventInfoMapper;
    @Autowired
    private AchievementUserMapper achievementUserMapper;
    @Autowired
    private UserEventPublisher publisher;

    @StreamListener(value = UserEventProcessor.EVENT_INPUT)
    public void eventHandle(@Payload BasisEvent event) {
        logger.info("进入事件");
        List<EventInfo> events = eventInfoMapper.selectByTypeIdAndCategory(event.getId(), EventEnum.EventCategory.ACHIEVEMENT.getCode());
        long now = System.currentTimeMillis();
        events.stream()
                .filter(i -> eventFilter(i, EventEnum.EventStatus.UP.getCode()))
                .filter(i -> eventFilter(i, EventEnum.EventState.FOREVER.getCode()) || (i.getStartTime().getTime() > now && i.getEndTime().getTime() < now))
                .forEach(i -> updateEventRecord(event, i));

    }

    @StreamListener(AchievementEventProcessor.ACHIEVEMENT_EVENT_INPUT)
    public void achievementHandle(@Payload BasisEvent event) {
        logger.info("进入成就");
        Integer userId = event.getCorrelationId();
        Integer eventId = event.getId();
        List<AchievementUser> result = achievementUserMapper.selectUncompleteByEventIdAndUserId(eventId, userId);
        Date date = new Date();
        result.forEach(i -> {
            i.setCompleteTime(date);
            i.setUpdateTime(date);
            i.setCreateTime(date);
            i.setCompleted(true);
            int count = achievementUserMapper.selectCountByAchieveId(i.getAchieveId());
            i.setCompleteNum(count + 1);
            achievementUserMapper.insertSelective(i);
        });
    }


    private boolean eventFilter(EventInfo i, Byte code) {
        return i.getStatus().equals(code);
    }

    private void updateEventRecord(BasisEvent event, EventInfo i) {
        Integer userId = event.getCorrelationId();
        AchievementUserRecord record = achievementUserRecordMapper.selectByUserIdAndEventId(userId, i.getId());
        if (record == null) {
            Date date = new Date();
            record = new AchievementUserRecord();
            record.setCount(0);
            record.setCycleNum(0);
            record.setCreateTime(date);
            record.setUpdateTime(date);
            record.setUserId(userId);
            record.setEventId(i.getId());
            achievementUserRecordMapper.insertSelective(record);
        }

        switch (eventCycleFilter(i, record)) {
            case 1:
                achievementUserRecordMapper.updateCountByPrimaryKey(record.getId(), record.getCount() + 1, record.getCycleNum() + 1);
                break;
            case 2:
                break;
            case 3:
                achievementUserRecordMapper.updateCountByPrimaryKey(record.getId(), record.getCount() + 1, 1);
                break;
            default:
        }
        event.setId(i.getId());
        publisher.achievementPublish(event);
    }

    private int eventCycleFilter(EventInfo e, AchievementUserRecord record) {
        if (!e.getContinueAble()) {
            return 1;
        }

        if (DateUtils.checkCycleEvent(e.getCycleType(), record.getUpdateTime())) {
            return e.getCycleNum() >= record.getCycleNum() ? 1 : 2;
        } else {
            return 3;
        }

    }
}
