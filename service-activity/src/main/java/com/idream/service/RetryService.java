package com.idream.service;

import com.idream.commons.lib.model.SendMqMessageRecord;

import java.util.List;

/**
 * @author: ZhengGaosheng
 * @date: 2018/6/11 19:49
 * @description:
 */
public interface RetryService {

    /**
     * 查询发送失败的消息放入MQ中
     */
    void retryMsg();
}

