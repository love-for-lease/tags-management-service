package com.matchmate.tagsmanagementservice.domain.models.tag;

import com.matchmate.tagsmanagementservice.common.domain.Aggregate;
import com.matchmate.tagsmanagementservice.domain.enums.TagStatusEnum;
import com.matchmate.tagsmanagementservice.domain.events.TagRegisteredEvent;
import lombok.Getter;

import static com.matchmate.tagsmanagementservice.common.AssertionConcern.assertArgumentNotBlank;

@Getter
public class Tag extends Aggregate<TagId> {
    private final String name;
    private TagStatusEnum status;

    public Tag(String name) {
        super(new TagId());
        assertArgumentNotBlank(name, "tag name must not be blank.");
        this.name = name;
        this.status = TagStatusEnum.CREATED;
    }

    public Tag(String name, TagStatusEnum status) {
        super(new TagId());

        assertArgumentNotBlank(name, "tag name must not be blank.");
        assertArgumentNotBlank(status.name(), "tag status must not be blank.");
        this.name = name;
        this.status = status;
    }

    public void register() {
        this.status = TagStatusEnum.CREATED;
        this.notify(new TagRegisteredEvent(this.name));
    }
}
