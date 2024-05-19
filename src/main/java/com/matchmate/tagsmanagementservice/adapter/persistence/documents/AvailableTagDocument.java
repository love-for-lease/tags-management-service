package com.matchmate.tagsmanagementservice.adapter.persistence.documents;

import com.matchmate.tagsmanagementservice.domain.enums.TagStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;
import java.util.UUID;

@Document("available-tag")
public class AvailableTagDocument {
    @Id
    private UUID id;
    private String name;
    private TagStatus status;
    @Field("created_at")
    private OffsetDateTime createdAt;
}
