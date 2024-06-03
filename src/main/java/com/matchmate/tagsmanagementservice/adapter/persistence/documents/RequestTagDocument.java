package com.matchmate.tagsmanagementservice.adapter.persistence.documents;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;

@Getter
@Document("request-tag")
public class RequestTagDocument {
    @Id
    private String id;
    private String name;
    private Long requests;
    @Field("requested_at")
    private ZonedDateTime requestedAt;

    public RequestTagDocument(String id, String name, Long requests, ZonedDateTime requestedAt) {
        this.id = id;
        this.name = name;
        this.requests = requests;
        this.requestedAt = requestedAt;
    }

    public RequestTagDocument(ZonedDateTime requestedAt, Long requests, String name) {
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
