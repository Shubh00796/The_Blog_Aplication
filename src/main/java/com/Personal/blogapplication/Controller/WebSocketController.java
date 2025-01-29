package com.Personal.blogapplication.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebSocketController {

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public String broadcastMessage(String message) {
        log.info("Broadcasting message: {}", message);
        return message;
    }
}