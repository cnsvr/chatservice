package com.example.chatservice.util;

import com.example.chatservice.events.UserJoinedToRoomEvent;
import com.example.chatservice.models.message.UserPresence;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EventPublishManager {
    private final ApplicationEventPublisher applicationEventPublisher;

    public EventPublishManager(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishUserJoinedToRoomEvent(String channelID, UserPresence userPresence) {
        UserJoinedToRoomEvent userJoinedToRoomEvent = new UserJoinedToRoomEvent();
        userJoinedToRoomEvent.setChannelID(channelID);
        userJoinedToRoomEvent.setUserPresence(userPresence);
        applicationEventPublisher.publishEvent(userJoinedToRoomEvent);
    }
}
