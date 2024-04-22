package com.example.chatservice.models.message;

import com.example.chatservice.models.base.BaseMessage;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class RoomMessage extends BaseMessage {
}
