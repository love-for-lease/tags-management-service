package com.matchmate.tagsmanagementservice.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;

@Configuration
class CustomCredentialsProviderConfig {

    @Bean
    public AwsCredentialsProvider customAwsCredentialsProvider() {
        return DefaultCredentialsProvider.builder().build();
    }
}