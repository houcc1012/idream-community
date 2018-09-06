package com.idream.commons.lib.dto.rabbitmq;

import java.io.Serializable;

/**
 * @author hejiang
 */
public class RabbitMqSendEntity implements Serializable {

    //交换机名称
    private String exchange;

    //路由键
    private String routingKey;

    //消息发送内容
    private String value;

    public RabbitMqSendEntity(String exchange, String routingKey, String value) {
        super();
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.value = value;
    }

    public RabbitMqSendEntity() {
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
