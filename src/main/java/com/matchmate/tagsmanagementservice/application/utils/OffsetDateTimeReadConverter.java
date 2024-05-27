package com.matchmate.tagsmanagementservice.application.utils;

import org.springframework.core.convert.converter.Converter;

import java.time.OffsetDateTime;

public class OffsetDateTimeReadConverter implements Converter<String, OffsetDateTime> {

    public OffsetDateTimeReadConverter() {
    }

    @Override
    public OffsetDateTime convert(String source) {
        return OffsetDateTime.parse(source);
    }
}
