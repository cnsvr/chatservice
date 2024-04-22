package com.example.chatservice.models.channel;

import com.example.chatservice.models.base.BaseChannel;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class GroupChannel extends BaseChannel {
    private String groupID;

    public GroupChannel() {
    }

    public GroupChannel(String groupID) {
        super(groupID, groupID); // TODO: sk can be different in the future
        this.groupID = groupID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }
}
