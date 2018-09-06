package com.idream.service.socket.model;

/**
 * socket Message返回内容对象
 *
 * @author hejiang
 */
public class ServerMessage {

    private String responseMessage;

    public ServerMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
