package com.matchmate.tagsmanagementservice.domain.models.tag;

import com.matchmate.tagsmanagementservice.common.domain.Aggregate;
import com.matchmate.tagsmanagementservice.domain.enums.TagStatus;
import com.matchmate.tagsmanagementservice.domain.events.TagRegisteredEvent;
import lombok.Getter;

@Getter
public class Tag extends Aggregate<TagId> {
    private final String name;
    private TagStatus status;

    public Tag(String name) {
        this.name = name;
    }

    public Tag(String name, TagStatus status) {
        this.name = name;
        this.status = status;
    }

    public void register() {
        this.getEvents().add(new TagRegisteredEvent(this.name));
    }
}
