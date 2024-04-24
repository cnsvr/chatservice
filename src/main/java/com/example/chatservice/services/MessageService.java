package com.example.chatservice.services;

import com.example.chatservice.models.message.UserPresence;
import com.example.chatservice.repositories.message.RoomMessageRepository;
import com.example.chatservice.requests.SendMessageRequest;
import com.example.chatservice.responses.SendMessageResponse;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessageService {
    private final RoomMessageRepository roomMessageRepository;
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    public MessageService(RoomMessageRepository roomMessageRepository, SimpMessageSendingOperations simpMessageSendingOperations) {
        this.roomMessageRepository = roomMessageRepository;
        this.simpMessageSendingOperations = simpMessageSendingOperations;
    }

    public SendMessageResponse sendMessage(Principal principal, SendMessageRequest sendMessageRequest) {
        SendMessageResponse sendMessageResponse = new SendMessageResponse();
        sendMessageResponse.setChannelID(sendMessageRequest.getChannelID());
        sendMessageResponse.setContent(sendMessageRequest.getContent());
        var userPresence = new UserPresence();
        userPresence.setUsername("yagmur");
        userPresence.setSessionId(principal.getName());
        sendMessageResponse.setSender(userPresence);
        sendMessageResponse.setTimestamp(System.currentTimeMillis());

        // Send to message topic and save it to database.
        Map<String, Object> headers = new HashMap<>();
        headers.put("sender", principal.getName());

        simpMessageSendingOperations.convertAndSend("/topic/" + sendMessageRequest.getChannelID(), sendMessageResponse, headers);
        // also send acknowledgement to the sender
        // simpMessageSendingOperations.convertAndSendToUser(sendMessageRequest.getSenderID(), "/queue/ack", "Message sent successfully");
        // roomMessageRepository.save(sendMessageRequest.getChannelID(), sendMessageRequest.getContent());
        return sendMessageResponse;
    }
}
