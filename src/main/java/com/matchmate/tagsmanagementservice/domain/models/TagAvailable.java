package com.matchmate.tagsmanagementservice.domain.models;

import com.matchmate.tagsmanagementservice.domain.enums.TagStatusEnum;
import lombok.Getter;

@Getter
public class TagAvailable {
    private final String name;
    private TagStatusEnum status;

    public TagAvailable(String name, TagStatusEnum status) {
        this.name = name;
        this.status = status;
    }

    public TagAvailable(String name) {
        this.name = name;
    }
}
