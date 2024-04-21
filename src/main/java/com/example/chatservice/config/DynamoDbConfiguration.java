package com.example.chatservice.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DynamoDbConfiguration {
    @Value("${aws.region}")
    private String region;
    private static final String LOCAL_ENDPOINT = "http://localhost:8000";

    @Bean
    @Profile("!local")
    public AmazonDynamoDB amazonDynamoDBClient() {
        return AmazonDynamoDBClientBuilder.standard().withRegion(region).build();
    }

    //  This bean is used to create a local instance of DynamoDB for testing purposes.
    @Bean
    @Profile("local")
    public AmazonDynamoDB amazonDynamoDBClientLocal() {
        return AmazonDynamoDBClient.builder()
                                   .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(LOCAL_ENDPOINT, region))
                                   .build();
    }
}
