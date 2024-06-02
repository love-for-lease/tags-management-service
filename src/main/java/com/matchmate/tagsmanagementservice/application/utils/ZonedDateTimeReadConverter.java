package com.matchmate.tagsmanagementservice.application.utils;

import org.springframework.core.convert.converter.Converter;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class ZonedDateTimeReadConverter implements Converter<Date, ZonedDateTime> {

    public ZonedDateTimeReadConverter() {
    }

    @Override
    public ZonedDateTime convert(Date source) {
        return source.toInstant().atZone(ZoneOffset.UTC);
    }
}
