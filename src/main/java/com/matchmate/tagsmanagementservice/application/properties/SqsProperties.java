package com.matchmate.tagsmanagementservice.application.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.aws")
@Getter
@Setter
@Component
public class SqsProperties {
    private String region;
    private String endpoint;
}
