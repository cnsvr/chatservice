package com.example.chatservice.models.channel;

import com.example.chatservice.models.base.BaseChannel;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class RoomChannel extends BaseChannel {
    private String roomID;
    public RoomChannel() {
    }

    public RoomChannel(String roomID) {
        super(roomID, roomID); // TODO: sk can be different in the future
        this.roomID = roomID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
}
