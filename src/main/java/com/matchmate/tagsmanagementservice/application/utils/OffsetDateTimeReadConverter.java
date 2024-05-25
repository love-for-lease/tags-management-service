package com.matchmate.tagsmanagementservice.application.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

public class OffsetDateTimeReadConverter implements Converter<String, OffsetDateTime> {

    public OffsetDateTimeReadConverter() {
    }

    @Override
    public OffsetDateTime convert(String source) {
        return OffsetDateTime.parse(source);
    }
}
