package com.example.chatservice.models.base;

import com.example.chatservice.enums.MessageCode;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class BaseMessage extends BaseModel {
    public static final String PK_PREFIX = "MESSAGE" + DELIMITER;
    public static final String SK_PREFIX = "MESSAGE" + DELIMITER;
    private String channelID;
    private String content;
    private MessageCode code;
    private Long timestamp; // Use it as a sort key to sort messages in a channel. Think about it.

    public BaseMessage() {
    }

    public BaseMessage(String channelID, String content, MessageCode code) {
        super(PK_PREFIX + channelID, SK_PREFIX + channelID); // TODO: sk can be different in the future
        this.channelID = channelID;
        this.content = content;
        this.code = code;
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
}
