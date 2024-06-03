package com.matchmate.tagsmanagementservice.application.config.mongodb;


import com.matchmate.tagsmanagementservice.application.utils.ZonedDateTimeReadConverter;
import com.matchmate.tagsmanagementservice.application.utils.ZonedDateTimeWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
public class MongoConfig {
    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new ZonedDateTimeReadConverter(),
                new ZonedDateTimeWriteConverter()
        ));
    }
}

