package com.example.chatservice.repositories.message;

import com.example.chatservice.models.message.RoomMessage;
import com.example.chatservice.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Repository
public class RoomMessageRepository extends BaseRepository<RoomMessage> {
    public RoomMessageRepository(Class<RoomMessage> type, DynamoDbEnhancedClient enhancedClient) {
        super(type, enhancedClient);
    }
}
