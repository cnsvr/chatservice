package com.example.chatservice.repositories.channel;

import com.example.chatservice.models.channel.RoomChannel;
import com.example.chatservice.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import static com.example.chatservice.models.base.BaseChannel.PK_PREFIX;
import static com.example.chatservice.models.base.BaseChannel.SK_PREFIX;

@Repository
public class RoomChannelRepository extends BaseRepository<RoomChannel> {
    public RoomChannelRepository(DynamoDbEnhancedClient enhancedClient) {
        super(RoomChannel.class, enhancedClient);
    }

    public RoomChannel findByRoomID(String roomID) {
        return table.getItem(Key.builder().partitionValue(PK_PREFIX + roomID).sortValue(SK_PREFIX + roomID).build());
    }

    public void deleteByRoomID(String roomID) {
        table.deleteItem(Key.builder().partitionValue(PK_PREFIX + roomID).sortValue(SK_PREFIX + roomID).build());
    }
}
