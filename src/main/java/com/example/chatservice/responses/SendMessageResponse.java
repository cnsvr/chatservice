package com.example.chatservice.responses;

import com.example.chatservice.enums.MessageCode;
import com.example.chatservice.models.message.UserPresence;

public class SendMessageResponse {
    private String channelID;
    private String messageID;
    private String content;
    private MessageCode messageCode;
    private UserPresence sender;
    private Long timestamp;

    public SendMessageResponse() {
    }

    public SendMessageResponse(String channelID, String messageID, String content, UserPresence sender, Long timestamp) {
        this.channelID = channelID;
        this.messageID = messageID;
        this.content = content;
        this.sender = sender;
        this.timestamp = timestamp;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(MessageCode messageCode) {
        this.messageCode = messageCode;
    }
}
