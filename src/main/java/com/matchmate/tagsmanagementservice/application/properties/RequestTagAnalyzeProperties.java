package com.matchmate.tagsmanagementservice.application.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.analyse-periodic-request-tags")
@Getter
@Setter
@Component
public class RequestTagAnalyzeProperties {
    private Integer rangeDateAnalyze;
    private Integer minimumRequest;
}
