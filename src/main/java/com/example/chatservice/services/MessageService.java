package com.example.chatservice.services;

import com.example.chatservice.enums.MessageCode;
import com.example.chatservice.models.message.RoomMessage;
import com.example.chatservice.models.message.UserPresence;
import com.example.chatservice.repositories.message.RoomMessageRepository;
import com.example.chatservice.requests.SendMessageRequest;
import com.example.chatservice.responses.SendMessageResponse;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;
import java.security.Principal;

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
        sendMessageResponse.setTimestamp(System.currentTimeMillis());


        var userPresence = new UserPresence();
        userPresence.setUsername("yagmur"); // TODO: update
        userPresence.setSessionId(principal.getName());
        userPresence.setUserId(principal.getName()); // TODO: update
        sendMessageResponse.setSender(userPresence);

        // Send to message topic and save it to database.

        simpMessageSendingOperations.convertAndSend("/topic/" + sendMessageRequest.getChannelID(), sendMessageResponse);
        // also send acknowledgement to the sender
        roomMessageRepository.save(new RoomMessage(sendMessageRequest.getChannelID(), sendMessageRequest.getContent(), MessageCode.CHAT_MESSAGE, userPresence));
        return sendMessageResponse;
    }
}
