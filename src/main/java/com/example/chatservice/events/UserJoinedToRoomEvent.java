package com.example.chatservice.events;

import com.example.chatservice.models.message.UserPresence;

public class UserJoinedToRoomEvent {
    private String channelID;
    private UserPresence userPresence;

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public UserPresence getUserPresence() {
        return userPresence;
    }

    public void setUserPresence(UserPresence userPresence) {
        this.userPresence = userPresence;
    }
}
