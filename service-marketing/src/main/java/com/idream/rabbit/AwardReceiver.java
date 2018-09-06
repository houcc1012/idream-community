package com.idream.rabbit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.idream.commons.lib.base.RabbitMqConstants;
import com.idream.commons.lib.dto.rabbitmq.IntegrationAwardAsyncInsertDto;
import com.idream.commons.lib.dto.rabbitmq.OpenLotteryAwardAsyncInsertDto;
import com.idream.service.AsyncTaskService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author hejiang
 */
@Component
public class AwardReceiver {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Resource
    private AsyncTaskService asyncTaskService;

    @RabbitListener(queues = RabbitMqConstants.AWARD_QUEUE, containerFactory = "awardListenContainer")
    public void awardInsertHandler(@Payload String msg,
                                   @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                                   Channel channel) throws IOException {
        logger.info("接收到来自" + RabbitMqConstants.AWARD_QUEUE + "队列的消息, data=" + msg);

        try {
            //处理奖券入库
            handlerAwardInsert(msg);
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            logger.error("处理用户中奖数据失败, msg = " + msg, e);
            channel.basicReject(deliveryTag, false);
        }
    }

    @RabbitListener(queues = RabbitMqConstants.DL_AWARD_QUEUE)
    public void dlAwardInsertHandler(@Payload String msg,
                                     @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                                     Channel channel) throws IOException {
        logger.info("接收到来自" + RabbitMqConstants.DL_AWARD_QUEUE + "队列的消息, data=" + msg);
        try {
            //处理奖券入库
            handlerAwardInsert(msg);
        } catch (Exception e) {
            logger.error("处理用户中奖数据失败, msg = " + msg, e);
        } finally {
            channel.basicAck(deliveryTag, true);
        }
    }

    /**
     * 奖券入库操作
     *
     * @param msg
     */
    private void handlerAwardInsert(String msg) {
        JSONObject json = JSON.parseObject(msg);
        String type = json.getString("type");//业务类型 1- 积分抽奖； 2-现场开奖
        if ("1".equals(type)) {
            IntegrationAwardAsyncInsertDto dto = JSON.parseObject(json.getString("obj"), IntegrationAwardAsyncInsertDto.class);
            asyncTaskService.doRecordIntegrationDraw(dto);
        } else if ("2".equals(type)) {
            OpenLotteryAwardAsyncInsertDto dto = JSON.parseObject(json.getString("obj"), OpenLotteryAwardAsyncInsertDto.class);
            asyncTaskService.doRecordOpenLotteryAward(dto);
        }
    }
}