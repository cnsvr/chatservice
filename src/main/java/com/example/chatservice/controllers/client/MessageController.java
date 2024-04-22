package com.example.chatservice.controllers.client;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @MessageMapping("/send-message")
    public void sendMessage() {
        // send message
    }
}
