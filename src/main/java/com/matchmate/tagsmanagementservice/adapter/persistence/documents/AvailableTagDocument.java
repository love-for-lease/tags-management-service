package com.matchmate.tagsmanagementservice.adapter.persistence.documents;

import com.matchmate.tagsmanagementservice.domain.enums.TagStatus;
import static java.time.ZoneOffset.UTC;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;

@Getter
@Document("available-tag")
public class AvailableTagDocument {
    @Id
    private String id;
    private final String name;
    private final TagStatus status;
    @Field("created_at")
    @CreatedDate
    private final OffsetDateTime createdAt = OffsetDateTime.now(UTC);

    public AvailableTagDocument(String name, TagStatus status) {
        this.name = name;
        this.status = status;
    }
}
