package com.example.chatservice.models.channel;

import com.example.chatservice.models.base.BaseChannel;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class DirectChannel extends BaseChannel {
    private String user1ID;
    private String user2ID;

    public DirectChannel() {
    }

    public DirectChannel(String user1ID) {
        super(user1ID, user1ID); // TODO: sk can be different in the future
        this.user1ID = user1ID;
    }

    public String getUser1ID() {
        return user1ID;
    }

    public void setUser1ID(String user1ID) {
        this.user1ID = user1ID;
    }

    public String getUser2ID() {
        return user2ID;
    }

    public void setUser2ID(String user2ID) {
        this.user2ID = user2ID;
    }
}
