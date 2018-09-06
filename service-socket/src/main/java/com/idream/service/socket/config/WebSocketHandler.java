package com.idream.service.socket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * webSocket端点 客户端根据这个url请求
 *
 * @author hejiang
 */
@ServerEndpoint("/websocket/{unionId}/{clientType}")
@Component
public class WebSocketHandler {

    private Logger log = LoggerFactory.getLogger(getClass().getName());

    //当前连接数
    private static int onlineCount = 0;

    //存放session的
    private static Map<String, CopyOnWriteArraySet<WebSocketHandler>> map = new HashMap<String, CopyOnWriteArraySet<WebSocketHandler>>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //用户身份id
    private String unionId;

    //客户端类型
    private String clientType;

    public static Map<String, CopyOnWriteArraySet<WebSocketHandler>> getMap() {
        return map;
    }

    /**
     * 建立连接
     *
     * @param unionId
     * @param clientType
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("unionId") String unionId, @PathParam("clientType") String clientType, Session session) {
        this.session = session;
        this.unionId = unionId;
        this.clientType = clientType;
        String key = unionId + "-" + clientType;
        CopyOnWriteArraySet<WebSocketHandler> webSocketSet = map.get(key);
        if (webSocketSet == null || webSocketSet.size() == 0) {
            webSocketSet = new CopyOnWriteArraySet<WebSocketHandler>();
        }
        webSocketSet.add(this);
        map.put(key, webSocketSet);
        addOnlineCount();           //在线数加1
        log.info("有新连接加入！当前在线人数为" + getOnlineCount() + "新连接用户编号：" + unionId + "客户端类型：" + clientType);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        String key = unionId + "-" + clientType;
        CopyOnWriteArraySet<WebSocketHandler> webSocketSet = map.get(key);
        if (webSocketSet == null || webSocketSet.size() == 0) {
            return;
        }
        webSocketSet.remove(this);  //从set中删除
        map.put(unionId, webSocketSet);
        subOnlineCount();           //在线数减1
        log.info("用户" + this.unionId + "连接关闭！当前在线人数为" + getOnlineCount() + "关闭连接用户编号：" + unionId);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        String key = unionId + "-" + clientType;
        log.info("来自客户端的消息:" + message);
        //群发消息
        CopyOnWriteArraySet<WebSocketHandler> webSocketSet = map.get(key);
        if (webSocketSet == null || webSocketSet.size() == 0) {
            return;
        }
        //心跳检测不发送给用户
        if (!"check connection".equals(message)) {
            for (WebSocketHandler item : webSocketSet) {
                try {
                    item.sendMessage(message);
                } catch (IOException e) {
                    log.error("推送消息异常", e);
                }
            }
        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("websocket发生错误", error);
    }

    /**
     * 同步消息推送
     *
     * @param message
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 异步消息推送
     *
     * @param message
     */
    public void sendAyscMessage(String message) throws IOException {
        this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketHandler.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketHandler.onlineCount--;
    }


}