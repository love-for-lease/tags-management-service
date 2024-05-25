package com.matchmate.tagsmanagementservice.application.config;

import com.matchmate.tagsmanagementservice.application.properties.SqsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
@RequiredArgsConstructor
public class SqsConfig {

    private final SqsProperties properties;

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .region(Region.of(properties.getRegion()))
                .endpointOverride(URI.create(properties.getEndpoint()))
                .build();
    }
}