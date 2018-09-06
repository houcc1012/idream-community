package com.idream.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author hejiang
 */
@EnableBinding({SocketProcessor.class})
@Component
public class SocketSendService {

    private static Logger logger = LoggerFactory.getLogger(SocketSendService.class);

    @Autowired
    private SocketProcessor socketProcessor;

    public boolean sendSocketMessage(String message, Byte type) {
        SocketStreamDto dto = new SocketStreamDto();
        dto.setMessage(message);
        dto.setDateTime(new Date());
        dto.setType(type);
        return socketProcessor.socketOutput().send(MessageBuilder.withPayload(dto).build());
    }
}
