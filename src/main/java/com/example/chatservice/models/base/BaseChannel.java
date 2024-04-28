package com.example.chatservice.models.base;

import com.example.chatservice.models.message.UserPresence;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbIgnore;
import java.util.List;
import java.util.UUID;

@DynamoDbBean
public class BaseChannel extends BaseModel {
    private String channelId;
    private List<UserPresence> members;
    private UserPresence self;

    public BaseChannel() {
    }

    public BaseChannel(String pk, String sk) {
        super(pk, sk);
        this.members = List.of();
        this.channelId = UUID.randomUUID().toString();
    }

    public List<UserPresence> getMembers() {
        return members;
    }

    public void setMembers(List<UserPresence> members) {
        this.members = members;
    }

    // It is ignored because it is not stored in the database
    @DynamoDbIgnore
    public UserPresence getSelf() {
        return self;
    }

    public void setSelf(UserPresence self) {
        this.self = self;
    }

    public void addPresence(UserPresence userPresence) {
        members.add(userPresence);
    }

    public void removePresence(UserPresence userPresence) {
        members.remove(userPresence);
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
