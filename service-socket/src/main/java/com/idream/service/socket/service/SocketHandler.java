package com.idream.service.socket.service;


import com.alibaba.fastjson.JSON;
import com.idream.service.socket.model.SocketStreamDto;
import com.idream.service.socket.rabbit.SocketProcessor;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author hejiang
 */
@Component
@EnableBinding({SocketProcessor.class})
public class SocketHandler {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass().getName());
//
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;

    @Resource
    private WebSocketService webSocketService;

    //监听socket通信
    @StreamListener(SocketProcessor.SOCKET_INPUT)
    public void sendSocketMessage(Message<SocketStreamDto> message) {
        SocketStreamDto dto = message.getPayload();
        logger.info("socket消息:" + JSON.toJSONString(dto));
        if (dto.getType() == 1) {//用户抽奖中奖广播
            String sendMessage = (String) dto.getMessage();
//            messagingTemplate.convertAndSend("/topic/drawBroadcast", new ServerMessage(sendMessage));
            webSocketService.sendMessageToClientType(sendMessage, "1");//给小程序推送
        }

    }

}
