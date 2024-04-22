package com.example.chatservice.responses;

import com.example.chatservice.models.message.UserPresence;

public class SendMessageResponse {
    private String channelID;
    private String messageID;
    private UserPresence sender;
    private Long timestamp;

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public UserPresence getSender() {
        return sender;
    }

    public void setSender(UserPresence sender) {
        this.sender = sender;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
