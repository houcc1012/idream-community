package com.idream.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.idream.commons.lib.base.RabbitMqConstants;
import com.idream.commons.lib.dto.rabbitmq.CommunityLifeSyncDto;
import com.idream.service.AppMyNeighborService;
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
public class CommunityLifeReceiver {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Resource
    private AppMyNeighborService appMyNeighborService;

    @RabbitListener(queues = RabbitMqConstants.COMMUNITY_LIFE_TIME_LINE_QUEUE, containerFactory = "communityLifeListenContainer")
    public void communityLifeTimeLineHandler(@Payload String msg,
                                             @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                                             Channel channel) throws IOException {
        logger.info("接收到来自" + RabbitMqConstants.COMMUNITY_LIFE_TIME_LINE_QUEUE + "队列的消息, data=" + msg);

        try {
            CommunityLifeSyncDto dto = JSON.parseObject(msg, CommunityLifeSyncDto.class);
            appMyNeighborService.insertCommunityLifeTimeLine(dto.getLifeId(), dto.getUserId(), dto.getPrivacyLevel(), dto.getRegionIds(), dto.getCreateTime());
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            logger.error("同步用户邻里圈动态时间线数据失败, msg = " + msg, e);
            channel.basicReject(deliveryTag, false);
        }
    }

    @RabbitListener(queues = RabbitMqConstants.DL_COMMUNITY_LIFE_TIME_LINE_QUEUE)
    public void dlCommunityLifeTimeLineHandler(@Payload String msg,
                                               @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                                               Channel channel) throws IOException {
        logger.info("接收到来自" + RabbitMqConstants.DL_COMMUNITY_LIFE_TIME_LINE_QUEUE + "队列的消息, data=" + msg);
        try {
            CommunityLifeSyncDto dto = JSON.parseObject(msg, CommunityLifeSyncDto.class);
            appMyNeighborService.insertCommunityLifeTimeLine(dto.getLifeId(), dto.getUserId(), dto.getPrivacyLevel(), dto.getRegionIds(), dto.getCreateTime());
        } catch (Exception e) {
            logger.error("同步用户邻里圈动态时间线数据失败, msg = " + msg, e);
        } finally {
            channel.basicAck(deliveryTag, true);
        }
    }
}