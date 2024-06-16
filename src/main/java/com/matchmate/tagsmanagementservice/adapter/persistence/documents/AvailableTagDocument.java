package com.matchmate.tagsmanagementservice.adapter.persistence.documents;

import com.matchmate.tagsmanagementservice.domain.enums.TagStatusEnum;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;
@Getter
@Document("available-tag")
public class AvailableTagDocument {
    @Id
    private String id;
    private String name;
    private TagStatusEnum status;
    @Field("created_at")
    private ZonedDateTime createdAt;
    public AvailableTagDocument(String id, String name, TagStatusEnum status) {
        this.createdAt = ZonedDateTime.now();
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public AvailableTagDocument(String name, TagStatusEnum status) {
        this.name = name;
        this.status = status;
    }

    public AvailableTagDocument(String id, ZonedDateTime createdAt, TagStatusEnum status, String name) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
        this.name = name;
    }

    public AvailableTagDocument() {}

}
