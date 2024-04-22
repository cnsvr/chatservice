package com.example.chatservice.controllers.client;

import com.example.chatservice.requests.SendMessageRequest;
import com.example.chatservice.services.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/send-message")
    public void sendMessage(SendMessageRequest sendMessageRequest) {
        // send message
        messageService.sendMessage(sendMessageRequest);
    }
}
