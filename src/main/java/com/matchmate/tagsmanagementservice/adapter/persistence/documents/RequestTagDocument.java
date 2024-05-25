package com.matchmate.tagsmanagementservice.adapter.persistence.documents;

import com.matchmate.tagsmanagementservice.domain.models.TagRequest;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;
import java.util.UUID;

@Document("request-tag")
@Getter
public class RequestTagDocument {
    @Id
    private UUID id;
    private final String name;
    private final Long requests;
    @Field("requested_at")
    private final OffsetDateTime requestedAt;

    public RequestTagDocument(String name, Long requests, OffsetDateTime requestedAt) {
        this.name = name;
        this.requests = requests;
        this.requestedAt = requestedAt;
    }
}
