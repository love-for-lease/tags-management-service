package com.matchmate.tagsmanagementservice.adapter.persistence.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;
import java.util.UUID;

@Document("request-tag")
public class RequestTagDocument {
    @Id
    private UUID id;
    private String name;
    private Long requests;
    @Field("requested_at")
    private OffsetDateTime requestedAt;
}
