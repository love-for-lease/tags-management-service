package com.matchmate.tagsmanagementservice.domain.models;

import com.matchmate.tagsmanagementservice.domain.enums.TagStatus;
import lombok.Getter;

@Getter
public class TagAvailable {
    private final String name;
    private TagStatus status;

    public TagAvailable(String name, TagStatus status) {
        this.name = name;
        this.status = status;
    }

    public TagAvailable(String name) {
        this.name = name;
    }
}
