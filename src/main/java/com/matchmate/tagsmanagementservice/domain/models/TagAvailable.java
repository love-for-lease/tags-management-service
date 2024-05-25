package com.matchmate.tagsmanagementservice.domain.models;

import com.matchmate.tagsmanagementservice.domain.enums.TagStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;

@Getter
@Builder
public class TagAvailable {
    private String name;
    private TagStatus status;
    @Field("created_at")
    private OffsetDateTime createdAt;
}
