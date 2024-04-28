package com.example.chatservice.models.base;

import com.example.chatservice.enums.MessageCode;
import com.example.chatservice.models.message.UserPresence;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class BaseMessage extends BaseModel {
    private String channelID;
    private String content;
    private MessageCode code;
    private UserPresence owner;
    private Long timestamp; // Use it as a sort key to sort messages in a channel. Think about it.

    public BaseMessage() {
    }

    public BaseMessage(String pk, String sk, String channelID, String content, MessageCode code, UserPresence owner) {
        super(pk, sk);
        this.channelID = channelID;
        this.content = content;
        this.code = code;
        this.owner = owner;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageCode getCode() {
        return code;
    }

    public void setCode(MessageCode code) {
        this.code = code;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public UserPresence getOwner() {
        return owner;
    }

    public void setOwner(UserPresence owner) {
        this.owner = owner;
    }
}
