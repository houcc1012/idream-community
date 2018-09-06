package com.idream.rabbit;

import com.idream.commons.lib.model.UserRequestRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author hejiang
 */
@EnableBinding({UserRequestRecordProcessor.class})
@Component
public class UserRequestRecordSendService {

    private static Logger logger = LoggerFactory.getLogger(UserRequestRecordSendService.class);

    @Autowired
    private UserRequestRecordProcessor userRequestRecordProcessor;

    //发送信息
    public boolean sendUserRequestRecord(UserRequestRecord dto) {
        return userRequestRecordProcessor.userRequestRecordOutput().send(MessageBuilder.withPayload(dto).build());
    }

}
