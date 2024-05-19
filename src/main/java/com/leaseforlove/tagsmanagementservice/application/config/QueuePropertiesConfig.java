package com.leaseforlove.tagsmanagementservice.application.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.queue.events")
@Getter
@Setter
@Component
public class QueuePropertiesConfig {
    private String tagRegisteredQueue;
}
