package com.example.chatservice.services;

import com.example.chatservice.repositories.message.RoomMessageRepository;
import com.example.chatservice.requests.SendMessageRequest;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final RoomMessageRepository roomMessageRepository;
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    public MessageService(RoomMessageRepository roomMessageRepository, SimpMessageSendingOperations simpMessageSendingOperations) {
        this.roomMessageRepository = roomMessageRepository;
        this.simpMessageSendingOperations = simpMessageSendingOperations;
    }


    public void sendMessage(SendMessageRequest sendMessageRequest) {
        // Send to message topic and save it to database.
        simpMessageSendingOperations.convertAndSend("/topic/" + sendMessageRequest.getChannelID(), sendMessageRequest.getContent());
        // also send acknowledgement to the sender
        // simpMessageSendingOperations.convertAndSendToUser(sendMessageRequest.getSenderID(), "/queue/ack", "Message sent successfully");
        // roomMessageRepository.save(sendMessageRequest.getChannelID(), sendMessageRequest.getContent());
    }
}
