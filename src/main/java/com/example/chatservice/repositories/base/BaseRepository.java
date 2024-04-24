package com.example.chatservice.repositories.base;

import com.example.chatservice.models.base.BaseModel;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import static com.example.chatservice.config.DynamoDbConfiguration.TABLE_NAME;

@Repository
public abstract class BaseRepository<T extends BaseModel> implements IBaseRepository<T> {
    public final DynamoDbTable<T> table;
    public final DynamoDbEnhancedClient enhancedClient;
    public final Class<T> type;

    public BaseRepository(Class<T> type, DynamoDbEnhancedClient enhancedClient) {
        this.type = type;
        this.enhancedClient = enhancedClient;
        this.table = enhancedClient.table(TABLE_NAME, TableSchema.fromBean(type));
    }

    @Override
    public T save(T entity) {
        table.putItem(entity);
        return entity;
    }
}
