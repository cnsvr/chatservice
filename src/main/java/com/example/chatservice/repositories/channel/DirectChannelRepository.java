package com.example.chatservice.repositories.channel;

import com.example.chatservice.models.channel.DirectChannel;
import com.example.chatservice.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Repository
public class DirectChannelRepository extends BaseRepository<DirectChannel> {
    public DirectChannelRepository(DynamoDbEnhancedClient enhancedClient) {
        super(DirectChannel.class, enhancedClient);
    }
}
