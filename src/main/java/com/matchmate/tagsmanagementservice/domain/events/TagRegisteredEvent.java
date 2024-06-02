package com.matchmate.tagsmanagementservice.domain.events;

import com.matchmate.tagsmanagementservice.common.event.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class TagRegisteredEvent extends DomainEvent {
    private final String tagName;

    public TagRegisteredEvent(String tagName) {
        super(UUID.randomUUID().toString(), TagRegisteredEvent.class.getSimpleName());
        this.tagName = tagName;
    }

    @Override
    public LocalDateTime occurredOn() {
        return LocalDateTime.now();
    }
}
