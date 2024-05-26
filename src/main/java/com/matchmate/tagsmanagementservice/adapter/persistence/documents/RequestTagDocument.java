package com.matchmate.tagsmanagementservice.adapter.persistence.documents;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;
import java.util.UUID;


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

    public RequestTagDocument(String id, String name, OffsetDateTime requestedAt, Long requests) {
        this.id = id;
        this.name = name;
        this.requests = (requests != null) ? requests : 1L ;
        this.requestedAt = requestedAt;
    }

    public RequestTagDocument(String name) {
        this.name = name;
        this.requests = 1L ;
    }

    public RequestTagDocument(String name, OffsetDateTime requestedAt, Long requests) {
        this.id = id;
        this.name = name;
        this.requests = (requests != null) ? requests : 1L ;
        this.requestedAt = requestedAt;
    }
    public RequestTagDocument incrementRequest() {
        requests++;
        return this;
    }
}
