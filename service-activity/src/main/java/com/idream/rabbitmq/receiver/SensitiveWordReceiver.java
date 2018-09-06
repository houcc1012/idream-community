package com.idream.rabbitmq.receiver;

import com.idream.commons.lib.mapper.SensitiveWordMapper;
import com.idream.commons.mvc.utils.SensitiveWordUtil;
import com.idream.rabbitmq.channel.SensitiveWordSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 敏感字队列处理
 * @author hejiang
 * @date 2018/8/28
 */

@EnableBinding(SensitiveWordSink.class)
@Component
public class SensitiveWordReceiver {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Resource
    private SensitiveWordMapper sensitiveWordMapper;

    @StreamListener(SensitiveWordSink.SENSITIVE_WORD_INPUT)
    public void hanle(Message<String> message) {
        logger.info("更新敏感字本地缓存 ....");
        SensitiveWordUtil.initTemporary(sensitiveWordMapper.findWordAllNotRepeat());
    }


}
