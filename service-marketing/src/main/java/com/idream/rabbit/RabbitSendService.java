package com.idream.rabbit;


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
import org.springframework.transaction.annotation.Transactional;

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
     * 积分抽奖
     *
     * @param msg
     */
    @Transactional
    public void sendIntegrationAward(String msg) {
        CorrelationData correlationId = new CorrelationData(UUIDUtils.getUUID());
        insertMqMessageRecord(msg, SystemEnum.SendMqMessageBusinessType.INTEGRATION_AWARD.getCode(), 3, correlationId.getId());
        rabbitTemplate.convertAndSend(RabbitMqConstants.AWARD_DIRECT_EXCHANGE, RabbitMqConstants.AWARD_ROUTING_KEY, msg, correlationId);
    }

    /**
     * 现场开奖
     *
     * @param msg
     */
    @Transactional
    public void sendLotteryDraw(String msg) {
        CorrelationData correlationId = new CorrelationData(UUIDUtils.getUUID());
        insertMqMessageRecord(msg, SystemEnum.SendMqMessageBusinessType.LOTTERY_DRAW.getCode(), 3, correlationId.getId());
        rabbitTemplate.convertAndSend(RabbitMqConstants.AWARD_DIRECT_EXCHANGE, RabbitMqConstants.AWARD_ROUTING_KEY, msg, correlationId);
    }

    /**
     * 中奖添加奖券消息重试
     *
     * @param msg
     * @param correlationId
     */
    @Transactional
    public void retrySendAward(String msg, String correlationId) {
        rabbitTemplate.convertAndSend(
                RabbitMqConstants.AWARD_DIRECT_EXCHANGE, RabbitMqConstants.AWARD_ROUTING_KEY, msg, new CorrelationData(correlationId));
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
