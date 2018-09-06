package com.idream.rabbit;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 短信 sc Stream 自定义通道
 *
 * @author hejiang
 */
public interface UserRequestRecordProcessor {

    //    String USER_REQUEST_RECORD_INPUT = "userRequestRecordInput";
//
    String USER_REQUEST_RECORD_OUTPUT = "userRequestRecordOutput";

    //    @Input(USER_REQUEST_RECORD_INPUT)
//    SubscribableChannel userRequestRecordInput();
//
    @Output(USER_REQUEST_RECORD_OUTPUT)
    MessageChannel userRequestRecordOutput();

}
