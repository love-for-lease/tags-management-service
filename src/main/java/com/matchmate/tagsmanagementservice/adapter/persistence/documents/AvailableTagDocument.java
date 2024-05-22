package com.matchmate.tagsmanagementservice.adapter.persistence.documents;

import com.matchmate.tagsmanagementservice.domain.enums.TagStatus;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;
import java.util.UUID;


@Getter
@Document("available-tag")
public class AvailableTagDocument {
    @Id
    private UUID id;
    private final String name;
    private final TagStatus status;
    @Field("created_at")
    @CreatedDate
    private OffsetDateTime createdAt;

    public AvailableTagDocument(String name, TagStatus status) {
        this.name = name;
        this.status = status;
    }
}
