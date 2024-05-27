package com.matchmate.tagsmanagementservice.adapter.persistence.documents;

import static java.time.ZoneOffset.UTC;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;

@Getter
@Document("request-tag")
@RequiredArgsConstructor
public class RequestTagDocument {
    @Id
    private final String id;
    private final String name;
    private final Long requests;
    @Field("requested_at")
    @CreatedDate
    private final OffsetDateTime requestedAt = OffsetDateTime.now(UTC);
}
