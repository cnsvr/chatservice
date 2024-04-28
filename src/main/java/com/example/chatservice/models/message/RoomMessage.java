package com.example.chatservice.models.message;

import com.example.chatservice.enums.MessageCode;
import com.example.chatservice.models.base.BaseMessage;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import static com.example.chatservice.constants.ModelConstant.*;

@DynamoDbBean
public class RoomMessage extends BaseMessage {
    public RoomMessage() {
    }

    public RoomMessage(String channelID, String content, MessageCode code, UserPresence owner) {
        super(getPartitionKey(), getSortKey(channelID, owner.getUserId()), channelID, content, code, owner);
    }

    public static String getPartitionKey() {
        return ROOM_MESSAGE_PK_PREFIX;
    }

    public static String getSortKey(String channelId, String userId) {
        return ROOM_MESSAGE_SK_PREFIX + DELIMETER + channelId + DELIMETER + userId;
    }
}
