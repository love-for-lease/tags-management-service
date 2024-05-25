package com.matchmate.tagsmanagementservice.domain.models;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@Builder
public class TagRequest {
    private String name;
    private Long requests;
    private OffsetDateTime requestedAt;
}
