package com.example.chatservice.config;

import com.example.chatservice.models.base.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import java.net.URI;

@Configuration
public class DynamoDbConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(DynamoDbConfiguration.class);
    public static final String TABLE_NAME = "ChatService";
    private static final String LOCAL_ENDPOINT = "http://localhost:8000";
    @Value("${aws.region}")
    private String region;

    @Bean
    @Profile("!local")
    public DynamoDbEnhancedClient amazonDynamoDBClient() {
        DynamoDbEnhancedClient client = DynamoDbEnhancedClient.builder()
                                                              .dynamoDbClient(
                                                               DynamoDbClient.builder()
                                                                             .region(Region.of(region))
                                                                             .build())
                                                              .build();
        // createTable(client);
        return client;
    }

    //  This bean is used to create a local instance of DynamoDB for testing purposes.
    @Bean
    @Profile("local")
    public DynamoDbEnhancedClient amazonDynamoDBClientLocal() {
        DynamoDbEnhancedClient client = DynamoDbEnhancedClient.builder()
                                                              .dynamoDbClient(
                                                               DynamoDbClient.builder()
                                                                             .endpointOverride(URI.create(LOCAL_ENDPOINT))
                                                                             .region(Region.of(region))
                                                                             .build())
                                                              .build();
        createTable(client);
        return client;
    }

    private void createTable(DynamoDbEnhancedClient client) {
        // Check if the table already exists
        DynamoDbTable<BaseModel> table = client.table(TABLE_NAME, TableSchema.fromBean(BaseModel.class));
        try {
            table.describeTable();
            logger.info("Table already exists");
        } catch (ResourceNotFoundException e) {
            logger.info("Table does not exist, creating table");
            table.createTable();
            logger.info("Table created");
        }

    }

}
