package com.matchmate.tagsmanagementservice.application.config.mongodb;

import com.matchmate.tagsmanagementservice.application.utils.DateTimeProviderImpl;
import com.matchmate.tagsmanagementservice.application.utils.OffsetDateTimeReadConverter;
import com.matchmate.tagsmanagementservice.application.utils.OffsetDateTimeWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
@EnableMongoAuditing(dateTimeProviderRef = "dateTimeProvider")
public class MongoConfig {
    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new OffsetDateTimeReadConverter(),
                new OffsetDateTimeWriteConverter()
        ));
    }

    @Bean("dateTimeProvider")
    public DateTimeProvider auditingDateTimeProvider() {
        return new DateTimeProviderImpl();
    }

}

