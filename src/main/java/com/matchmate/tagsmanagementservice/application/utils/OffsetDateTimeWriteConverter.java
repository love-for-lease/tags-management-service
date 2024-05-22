package com.matchmate.tagsmanagementservice.application.utils;

import org.springframework.core.convert.converter.Converter;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class OffsetDateTimeWriteConverter implements Converter<OffsetDateTime, String> {

    public OffsetDateTimeWriteConverter() {
    }

    @Override
    public String convert(OffsetDateTime source) {
        return source.toInstant().atZone(ZoneOffset.UTC).toString();
    }
}
