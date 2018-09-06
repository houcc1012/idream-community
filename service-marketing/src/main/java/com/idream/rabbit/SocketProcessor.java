package com.idream.rabbit;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * socket sc Stream 自定义通道
 *
 * @author hejiang
 */
public interface SocketProcessor {


    String SOCKET_INPUT = "socketInput";

    String SOCKET_OUTPUT = "socketOutput";

    @Input(SOCKET_INPUT)
    SubscribableChannel socketInput();

    @Output(SOCKET_OUTPUT)
    MessageChannel socketOutput();
}
