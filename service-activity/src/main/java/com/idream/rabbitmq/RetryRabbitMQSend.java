package com.idream.rabbitmq;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;
import com.idream.commons.lib.dto.mail.SendMailParams;
import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.exception.BusinessException;
import com.idream.commons.lib.mapper.SendMqMessageRecordMapper;
import com.idream.commons.lib.model.SendMqMessageRecord;
import com.idream.feign.FeignSendMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 重试rabbitmq失败发送
 *
 * @author hejiang
 */
@Component
public class RetryRabbitMQSend {

    private static final Logger logger = LoggerFactory.getLogger(RetryRabbitMQSend.class);

    // 重试次数
    private static final int ATTEMPT_NUMBER = 5;

    //重试间隔时间,单位秒
    private static final long RETRY_WAIT_SECONDS = 5L;

    @Resource
    private SendMqMessageRecordMapper sendMqMessageRecordMapper;

    @Resource
    @Lazy
    private RabbitSendService rabbitSendService;

    @Resource
    private FeignSendMailService feignSendMailService;

    @Async
    public void handlerMqAck(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            logger.info("消息发送成功:" + correlationData);
            sendMqMessageRecordMapper.updateStatusByMsgId(SystemEnum.SendMqMessageStatus.SEND_SUCCESS.getCode(), correlationData.getId());
        } else {
            logger.info("消息发送失败:" + cause + "correlationId=" + correlationData.getId());
            //进入重试机制
            String correlationId = correlationData.getId();
            SendMqMessageRecord record = sendMqMessageRecordMapper.selectByMsgId(correlationId);
            if (record != null && record.getRetryNo() == 0) {
                sendMqMessageRecordMapper.updateStatusByMsgId(SystemEnum.SendMqMessageStatus.SEND_FAIL.getCode(), correlationId);
                retrySend(correlationId);
            }
        }
    }


    private void retrySend(final String msgId) {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(Predicates.<Boolean>isNull())// 返回true 需要重试
                .retryIfResult(Predicates.<Boolean>equalTo(true))
                .retryIfExceptionOfType(BusinessException.class)// 设置异常重试源
                .retryIfRuntimeException()// 设置异常重试源
                .withStopStrategy(StopStrategies.stopAfterAttempt(ATTEMPT_NUMBER))// 设置重试次数
                .withWaitStrategy(WaitStrategies.fixedWait(RETRY_WAIT_SECONDS, TimeUnit.SECONDS))// 设置每次重试间隔
                .build();
        //重试操作
        try {
            retryer.call(new Callable<Boolean>() {
                int retryNo = 0;

                @Override
                public Boolean call() throws Exception {
                    retryNo++;
                    logger.info("mq消息第{}次重试... msgId=" + msgId, retryNo);
                    SendMqMessageRecord record = sendMqMessageRecordMapper.selectByMsgId(msgId);
                    if (record == null) {
                        logger.info("rabbitmq 消息发送记录未找到! msgID = " + msgId);
                        return false;
                    }
                    if (SystemEnum.SendMqMessageStatus.SEND_SUCCESS.getCode().equals(record.getStatus())) {
                        logger.info("rabbitmq 消息发送成功,无需重试! msgID = " + msgId);
                        return false;
                    }
                    if (retryNo > record.getRetryMaxNo()) {
                        logger.info("超过最大重试次数，重试暂停，发送邮件通知人工处理! msgID = " + msgId);
                        sendMail(msgId);
                        return false;
                    }
                    //修改重试次数
                    sendMqMessageRecordMapper.addOneRetryNoByMsgId(msgId, retryNo);

                    if (SystemEnum.SendMqMessageBusinessType.COMMUNITY_LIFR.getCode().equals(record.getBusinessType())) {
                        rabbitSendService.retrySendCommunityLife(record.getContent(), msgId);
                    } else if (SystemEnum.SendMqMessageBusinessType.WANGYI_IM.getCode().equals(record.getBusinessType())) {
                        rabbitSendService.retrySendWangyiIm(record.getContent(), msgId);
                    }
                    logger.info("mq消息开始重试结束!");
                    return true;
                }
            });
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        } catch (RetryException e) {
            logger.error("重试发送rabbitmq消息失败,需要发送提醒邮件. msgID = " + msgId);
            sendMail(msgId);
        }
    }

    /**
     * 发送邮件
     *
     * @param msgId
     */
    private void sendMail(String msgId) {
        //处理失败, 发送邮件
        SendMailParams sendMailParams = new SendMailParams();
        sendMailParams.setSubject("MQ消息发送失败");
        sendMailParams.setText("MQ消息发送失败 msgID:" + msgId);
        sendMailParams.setToMailUser("75201270@qq.com");
        sendMailParams.setMailAttachments(null);
        feignSendMailService.sendSimpleMail(sendMailParams);
    }
}
