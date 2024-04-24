package com.example.chatservice.models.channel;

import com.example.chatservice.constants.ModelConstant;
import com.example.chatservice.models.base.BaseChannel;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class RoomChannel extends BaseChannel {
    private String roomID;

    public RoomChannel() {
    }

    public RoomChannel(String roomID) {
        super(ModelConstant.ROOM_CHANNEL_PK_PREFIX, ModelConstant.ROOM_CHANNEL_SK_PREFIX + ModelConstant.DELIMETER + roomID);
        this.roomID = roomID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public static String getPartitionKey() {
        return ModelConstant.ROOM_CHANNEL_PK_PREFIX;
    }
    public static String getSortKey(String roomID) {
        return ModelConstant.ROOM_CHANNEL_SK_PREFIX + ModelConstant.DELIMETER + roomID;
    }
}
