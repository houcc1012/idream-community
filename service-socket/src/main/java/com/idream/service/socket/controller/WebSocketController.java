package com.idream.service.socket.controller;

import com.idream.service.socket.service.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author hejiang
 */
@Controller
public class WebSocketController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private WebSocketService webSocketService;

    @GetMapping("/")
    public String index() {
        return "webSocket";
    }

    @GetMapping("/socketPage")
    public String socketPage() {
        return "socketpage";
    }

    @GetMapping("/send/message")
    public void sendMessage(@RequestParam String message) {
        webSocketService.sendMessageToClientType(message, "1");//给小程序推送
    }

}
