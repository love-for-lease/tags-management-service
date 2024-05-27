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

        if(values.isEmpty()) {
            throw new IllegalArgumentException("tags values must not be empty.");
        }

        List<Tag> tags = values.stream().map(value -> new Tag(
                value,
                TagStatus.CREATED)).toList();

        tags.forEach(tag -> {
            tag.register();
            tag.raiseEvent();
        });
        tagPersistencePort.save(tags);
    }
}
