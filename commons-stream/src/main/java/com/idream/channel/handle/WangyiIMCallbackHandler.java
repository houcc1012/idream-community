package com.idream.channel.handle;

import com.alibaba.fastjson.JSON;
import com.idream.commons.lib.dto.wangyi.WangyiIMNoticeCCParams;
import com.idream.commons.lib.enums.WangYiEnum;
import com.idream.rabbit.WangyiIMCallBackProcessor;
import com.idream.service.WangYiCallBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author hejiang
 */
@EnableBinding({WangyiIMCallBackProcessor.class})
@Component
public class WangyiIMCallbackHandler {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Resource
    private WangYiCallBackService wangYiCallBackService;

    @StreamListener(WangyiIMCallBackProcessor.WANGYI_IM_CALLBACK_INPUT)
    public void noticeCallbackHandler(Message<WangyiIMNoticeCCParams> message) {
        logger.info("处理网易消息回调开始");
        WangyiIMNoticeCCParams params = message.getPayload();
        if (params != null) {
            //只处理会话类型的消息
            if ("1".equals(params.getEventType())) {
                //p2p消息
                if (WangYiEnum.ConvType.PERSON.toString().equals(params.getConvType())) {
                    wangYiCallBackService.insertWangyiImUserRecord(params);
                }
                //群消息
                else if (WangYiEnum.ConvType.TEAM.toString().equals(params.getConvType())) {
                    wangYiCallBackService.insertWangyiImGroupRecord(params);
                } else {
                    logger.info("非会话类型消息不入库，data:{}", JSON.toJSONString(params));
                }
            }
        }
    }

}
