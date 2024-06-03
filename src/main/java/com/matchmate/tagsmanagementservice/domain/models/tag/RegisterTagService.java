package com.matchmate.tagsmanagementservice.domain.models.tag;

import com.matchmate.tagsmanagementservice.common.event.DomainEventHandler;
import com.matchmate.tagsmanagementservice.domain.events.TagRegisteredEvent;
import com.matchmate.tagsmanagementservice.domain.ports.RegisterTagPort;
import com.matchmate.tagsmanagementservice.domain.ports.TagPersistencePort;

import java.util.List;

public class RegisterTagService implements RegisterTagPort {
    private final TagPersistencePort tagPersistencePort;
    private final DomainEventHandler<TagRegisteredEvent> domainEventHandler;

    public RegisterTagService(TagPersistencePort tagPersistencePort, DomainEventHandler<TagRegisteredEvent> domainEventHandler) {
        this.tagPersistencePort = tagPersistencePort;
        this.domainEventHandler = domainEventHandler;
    }

    @Override
    public void register(List<String> values) {

        if (values.isEmpty()) {
            throw new IllegalArgumentException("tags values must not be empty.");
        }

        List<Tag> tags = values.stream().map(Tag::new).toList();

        tagPersistencePort.save(tags);

        tags.forEach(t -> t.register(TagRegisteredEvent.class.getSimpleName(), data ->
                domainEventHandler.handleEvent((TagRegisteredEvent) data)));
        tags.forEach(Tag::register);
    }
}


