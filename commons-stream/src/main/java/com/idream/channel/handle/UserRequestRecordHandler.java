package com.idream.channel.handle;

import com.idream.commons.lib.model.UserRequestRecord;
import com.idream.rabbit.UserRequestRecordProcessor;
import com.idream.service.UserRequestRecordService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author hejiang
 */
@Component
@EnableBinding({UserRequestRecordProcessor.class})
public class UserRequestRecordHandler {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private UserRequestRecordService userRequestRecordService;

    /**
     * @Author: hejiang
     * @Description: 处理用户日志
     * @Date: 10:37 2018/5/22
     */
    @StreamListener(UserRequestRecordProcessor.USER_REQUEST_RECORD_INPUT)
    public void hanle(Message<UserRequestRecord> message) {
        logger.info("记录请求日志。。。");
        UserRequestRecord record = message.getPayload();
        record.setCreateTime(new Date());
        userRequestRecordService.insertUserRequestsRecord(record);
    }
}
