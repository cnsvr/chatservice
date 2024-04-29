package com.example.chatservice.services;

import com.example.chatservice.enums.MessageCode;
import com.example.chatservice.events.UserJoinedToRoomEvent;
import com.example.chatservice.events.UserLeftRoomEvent;
import com.example.chatservice.models.message.RoomMessage;
import com.example.chatservice.models.message.UserPresence;
import com.example.chatservice.repositories.message.RoomMessageRepository;
import com.example.chatservice.requests.SendMessageRequest;
import com.example.chatservice.responses.SendMessageResponse;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
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
        sendMessageResponse.setMessageCode(MessageCode.CHAT_MESSAGE);

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

    @EventListener
    @Async
    public void sendUserJoinedToRoomMessage(UserJoinedToRoomEvent userJoinedToRoomEvent) {
        // Send user presence to the room topic
        SendMessageResponse sendMessageResponse = new SendMessageResponse();
        sendMessageResponse.setChannelID(userJoinedToRoomEvent.getChannelID());
        sendMessageResponse.setContent("User joined to the room");
        sendMessageResponse.setSender(userJoinedToRoomEvent.getUserPresence());
        sendMessageResponse.setTimestamp(System.currentTimeMillis());
        sendMessageResponse.setMessageCode(MessageCode.USER_JOINED);

        simpMessageSendingOperations.convertAndSend("/topic/" + userJoinedToRoomEvent.getChannelID(), sendMessageResponse);
    }

    @EventListener
    @Async
    public void sendUserLeftRoomMessage(UserLeftRoomEvent userLeftRoomEvent) {
        // Send user presence to the room topic
        SendMessageResponse sendMessageResponse = new SendMessageResponse();
        sendMessageResponse.setChannelID(userLeftRoomEvent.getChannelID());
        sendMessageResponse.setContent("User left the room");
        sendMessageResponse.setSender(userLeftRoomEvent.getUserPresence());
        sendMessageResponse.setTimestamp(System.currentTimeMillis());
        sendMessageResponse.setMessageCode(MessageCode.USER_LEFT);

        simpMessageSendingOperations.convertAndSend("/topic/" + userLeftRoomEvent.getChannelID(), sendMessageResponse);
    }
}
