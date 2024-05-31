package com.matchmate.tagsmanagementservice.adapter.persistence.documents;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;

import static java.time.ZoneOffset.UTC;

@Getter
@Document("request-tag")
public class RequestTagDocument {
    @Id
    private String id;
    private String name;
    private Long requests;
    @Field("requested_at")
    @CreatedDate
    private OffsetDateTime requestedAt;

    public RequestTagDocument(String id, String name, Long requests, OffsetDateTime requestedAt) {
        this.id = id;
        this.name = name;
        this.requests = requests;
        this.requestedAt = requestedAt;
    }

    public RequestTagDocument(OffsetDateTime requestedAt, Long requests, String name) {
        this.requestedAt = requestedAt;
        this.requests = requests;
        this.name = name;
    }

    public RequestTagDocument(String id, String name, Long requests) {
        this.id = id;
        this.requests = requests;
        this.name = name;
    }

    public RequestTagDocument(){}
}
