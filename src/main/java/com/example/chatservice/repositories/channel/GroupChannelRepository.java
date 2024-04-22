package com.example.chatservice.repositories.channel;

import com.example.chatservice.models.channel.GroupChannel;
import com.example.chatservice.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Repository
public class GroupChannelRepository extends BaseRepository<GroupChannel> {
    public GroupChannelRepository(DynamoDbEnhancedClient enhancedClient) {
        super(GroupChannel.class, enhancedClient);
    }
}
