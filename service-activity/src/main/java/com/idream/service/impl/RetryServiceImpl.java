package com.idream.service.impl;

import com.idream.commons.lib.enums.SystemEnum;
import com.idream.commons.lib.mapper.SendMqMessageRecordMapper;
import com.idream.commons.lib.model.SendMqMessageRecord;
import com.idream.rabbitmq.RabbitSendService;
import com.idream.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ZhengGaosheng
 * @date: 2018/6/11 19:50
 * @description:
 */
@Service
public class RetryServiceImpl implements RetryService {

    @Autowired
    private SendMqMessageRecordMapper sendMqMessageRecordMapper;

    @Autowired
    private RabbitSendService rabbitSendService;

    @Override
    public void retryMsg() {
        List<SendMqMessageRecord> sendMqMessageRecords = sendMqMessageRecordMapper.selectUnsuccessfulMsg();
        //放入MQ中
        for (SendMqMessageRecord sendMqMessageRecord : sendMqMessageRecords) {
            if (sendMqMessageRecord.getBusinessType().equals(SystemEnum.SendMqMessageBusinessType.WANGYI_IM.getCode())) {
                rabbitSendService.sendWangyiIm(sendMqMessageRecord.getContent());
            } else if (sendMqMessageRecord.getBusinessType().equals(SystemEnum.SendMqMessageBusinessType.COMMUNITY_LIFR.getCode())) {
                rabbitSendService.sendCommunityLife(sendMqMessageRecord.getContent());
            }
        }
    }
}

