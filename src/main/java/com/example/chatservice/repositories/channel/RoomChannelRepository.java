package com.example.chatservice.repositories.channel;

import com.example.chatservice.models.channel.RoomChannel;
import com.example.chatservice.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import java.util.List;

@Repository
public class RoomChannelRepository extends BaseRepository<RoomChannel> {
    public RoomChannelRepository(DynamoDbEnhancedClient enhancedClient) {
        super(RoomChannel.class, enhancedClient);
    }

    public List<RoomChannel> findAll() {
        QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder().partitionValue(RoomChannel.getPartitionKey()).build());
        QueryEnhancedRequest query = QueryEnhancedRequest.builder().queryConditional(queryConditional).build();
        return table.query(query).items().stream().toList();
    }

    public RoomChannel findByRoomID(String roomID) {
        return table.getItem(Key.builder().partitionValue(RoomChannel.getPartitionKey()).sortValue(RoomChannel.getSortKey(roomID)).build());
    }

    public void deleteByRoomID(String roomID) {
        table.deleteItem(Key.builder().partitionValue(RoomChannel.getPartitionKey()).sortValue(RoomChannel.getSortKey(roomID)).build());
    }
}
