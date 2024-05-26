package com.matchmate.tagsmanagementservice.application.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.events.queues")
@Getter
@Setter
@Component
public class QueueProperties {
    private String tagRegisteredQueue;
}
