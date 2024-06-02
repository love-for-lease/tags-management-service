package com.matchmate.tagsmanagementservice.application.utils;

import org.springframework.core.convert.converter.Converter;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class ZonedDateTimeWriteConverter implements Converter<ZonedDateTime, Date> {

    public ZonedDateTimeWriteConverter() {
    }

    @Override
    public Date convert(ZonedDateTime source) {
        ZonedDateTime zonedDateTime = source.minusHours(3);
        return Date.from(zonedDateTime.toInstant().atZone(ZoneOffset.UTC).toInstant());
    }
}
