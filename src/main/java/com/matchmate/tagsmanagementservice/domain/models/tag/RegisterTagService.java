package com.matchmate.tagsmanagementservice.domain.models.tag;

import com.matchmate.tagsmanagementservice.common.AssertionConcern;
import com.matchmate.tagsmanagementservice.domain.enums.TagStatus;
import com.matchmate.tagsmanagementservice.domain.ports.RegisterTagPort;
import com.matchmate.tagsmanagementservice.domain.ports.TagPersistencePort;

import java.util.List;

public class RegisterTagService extends AssertionConcern implements RegisterTagPort {
    private final TagPersistencePort tagPersistencePort;

    public RegisterTagService(TagPersistencePort tagPersistencePort) {
        this.tagPersistencePort = tagPersistencePort;
    }

    @Override
    public void register(List<String> values) {
        this.assertListNotEmpty(values, "tags values must not be empty.");

        List<Tag> tags = values.stream().map(value -> new Tag(
                value,
                TagStatus.CREATED)).toList();

        tags.forEach(tag -> {
            this.assertArgumentNotNull(tag.getName(), "tag name must not be null.");
            tag.register();
            tag.raiseEvent();
        });
    }
}
