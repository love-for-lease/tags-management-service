package com.matchmate.tagsmanagementservice.application.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.analyse-periodic-request-tags.range-date-analyze")
@Getter
@Setter
@Component
public class DateRangeAnalyzeProperties {
    private String dateRangeDays;
}
