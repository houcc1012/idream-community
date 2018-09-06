package com.idream.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.idream.commons.lib.base.RabbitMqConstants;
import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.mapper.SendMqMessageRecordMapper;
import com.idream.commons.lib.model.SendMqMessageRecord;
import com.idream.commons.lib.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @author hejiang
 */
@Component
public class RabbitSendService implements RabbitTemplate.ConfirmCallback {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SendMqMessageRecordMapper sendMqMessageRecordMapper;

    @Resource
    private RetryRabbitMQSend retryRabbitMQSend;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        retryRabbitMQSend.handlerMqAck(correlationData, ack, cause);
    }

    /**
     * 邻里圈同步时间线发送消息
     *
     * @param msg
     */
    public void sendCommunityLife(String msg) {
        CorrelationData correlationId = new CorrelationData(UUIDUtils.getUUID());
        insertMqMessageRecord(msg, SystemEnum.SendMqMessageBusinessType.COMMUNITY_LIFR.getCode(), 3, correlationId.getId());
        rabbitTemplate.convertAndSend(RabbitMqConstants.COMMUNITY_LIFE_DIRECT_EXCHANGE, RabbitMqConstants.COMMUNITY_LIFE_ROUTING_KEY, msg, correlationId);
    }

    /**
     * 网易IM消息
     *
     * @param msg
     */
    public void sendWangyiIm(String msg) {
        CorrelationData correlationId = new CorrelationData(UUIDUtils.getUUID());
        insertMqMessageRecord(msg, SystemEnum.SendMqMessageBusinessType.WANGYI_IM.getCode(), 3, correlationId.getId());
        rabbitTemplate.convertAndSend(RabbitMqConstants.IM_DIRECT_EXCHANGE, RabbitMqConstants.IM_ROUTING_KEY, msg, correlationId);
    }

    /**
     * 网易消息mq发送失败重试
     *
     * @param msg
     * @param correlationId
     */
    public void retrySendWangyiIm(String msg, String correlationId) {
        rabbitTemplate.convertAndSend(
                RabbitMqConstants.IM_DIRECT_EXCHANGE, RabbitMqConstants.IM_ROUTING_KEY, msg, new CorrelationData(correlationId));
    }

    /**
     * 邻里圈数据同步mq发送失败重试
     *
     * @param msg
     * @param correlationId
     */
    public void retrySendCommunityLife(String msg, String correlationId) {
        rabbitTemplate.convertAndSend(
                RabbitMqConstants.COMMUNITY_LIFE_DIRECT_EXCHANGE, RabbitMqConstants.COMMUNITY_LIFE_ROUTING_KEY, msg, new CorrelationData(correlationId));
    }


    public void send(String exchange, String routingKey, String msg) {
        CorrelationData correlationId = new CorrelationData(UUIDUtils.getUUID());
        rabbitTemplate.convertAndSend(exchange, routingKey, msg, correlationId);
    }

    public void send(String exchange, String routingKey, Object obj) {
        CorrelationData correlationId = new CorrelationData(UUIDUtils.getUUID());
        String msg = JSON.toJSONString(obj);
        rabbitTemplate.convertAndSend(exchange, routingKey, msg, correlationId);
    }

    /**
     * 新增mq发送记录
     *
     * @param msg
     * @param businessType
     * @param retryMaxNo
     * @param msgId
     */
    private int insertMqMessageRecord(String msg, Byte businessType, int retryMaxNo, String msgId) {
        SendMqMessageRecord record = new SendMqMessageRecord();
        record.setContent(msg);
        Date date = new Date();
        record.setCreateTime(date);
        record.setBusinessType(businessType);
        record.setMsgId(msgId);
        record.setStatus(SystemEnum.SendMqMessageStatus.SENDING.getCode());
        record.setUpdateTime(date);
        record.setRetryMaxNo((short) retryMaxNo);
        return sendMqMessageRecordMapper.insertSelective(record);
    }
}
