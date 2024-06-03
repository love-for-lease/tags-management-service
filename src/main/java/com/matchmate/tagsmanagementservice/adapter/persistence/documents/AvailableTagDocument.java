package com.matchmate.tagsmanagementservice.adapter.persistence.documents;

import com.matchmate.tagsmanagementservice.domain.enums.TagStatus;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Document("available-tag")
public class AvailableTagDocument {
    @Id
    private String id;
    private final String name;
    private final TagStatus status;
    @Field("created_at")
    private final ZonedDateTime createdAt = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

    public AvailableTagDocument(String id, String name, TagStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public AvailableTagDocument(String name, TagStatus status) {
        this.name = name;
        this.status = status;
    }
}
