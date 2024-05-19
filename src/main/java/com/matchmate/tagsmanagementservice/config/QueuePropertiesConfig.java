package com.matchmate.tagsmanagementservice.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "app.queue")
@AllArgsConstructor(onConstructor_ = @ConstructorBinding)
@Getter
public class QueuePropertiesConfig {
    private String tagsRegisteredQueue;
}
