package com.idream.service.socket.service;

import com.idream.service.socket.config.WebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * websocket服务
 *
 * @author hejiang
 */
@Service("webSocketService")
public class WebSocketService {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    /**
     * 給指定客户端用户发送消息
     *
     * @param message
     * @param clientType
     */
    public void sendMessageToClientType(String message, String clientType) {
        Map<String, CopyOnWriteArraySet<WebSocketHandler>> map = WebSocketHandler.getMap();
        if (map == null || map.isEmpty()) {
            return;
        }
        map.forEach((key, value) -> {
            if (key.endsWith("-" + clientType)) {
                value.forEach((item) -> {
                    try {
                        item.sendAyscMessage(message);
                    } catch (IOException e) {
                        logger.error("给指定客户端消息推送失败！clientType=" + clientType + ",message=" + message, e);
                    }
                });
            }
        });
    }

    /**
     * 给所有用户发送消息
     *
     * @param message
     */
    public void sendMessageToAll(String message) {
        Map<String, CopyOnWriteArraySet<WebSocketHandler>> map = WebSocketHandler.getMap();
        if (map == null || map.isEmpty()) {
            return;
        }
        map.forEach((key, value) -> value.forEach((item) -> {
            try {
                item.sendAyscMessage(message);
            } catch (IOException e) {
                logger.error("给指定客户端消息推送失败,message=" + message, e);
            }
        }));
    }

    /**
     * 根据用户编号给客户端推送消息
     *
     * @param message
     * @param userId
     * @param clientType
     */
    public void sendMessageToUser(String message, String userId, String clientType) {
        String key = userId + "-" + clientType;
        CopyOnWriteArraySet<WebSocketHandler> webSocketSet = WebSocketHandler.getMap().get(key);
        if (webSocketSet == null || webSocketSet.size() == 0) {
            return;
        }
        for (WebSocketHandler item : webSocketSet) {
            try {
                item.sendAyscMessage(message);
            } catch (IOException e) {
                logger.error("消息推送失败！userid=" + userId + ",message=" + message, e);
                continue;
            }
        }
    }

}
