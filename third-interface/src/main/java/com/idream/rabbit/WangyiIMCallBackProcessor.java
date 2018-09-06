package com.idream.rabbit;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 网易消息抄送
 *
 * @author hejiang
 */
public interface WangyiIMCallBackProcessor {

//    String WANGYI_IM_CALLBACK_INPUT = "wangyiImCallbackInput";

    String WANGYI_IM_CALLBACK_OUTPUT = "wangyiImCallbackOutput";

//    @Input(value = WANGYI_IM_CALLBACK_INPUT)
//    SubscribableChannel noticeCallbackInput();

    @Output(value = WANGYI_IM_CALLBACK_OUTPUT)
    MessageChannel noticeCallbackOutput();

}
