package com.matchmate.tagsmanagementservice.domain.events;

import com.matchmate.tagsmanagementservice.common.event.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class TagRegisteredEvent extends DomainEvent {
    private final String name;

    public TagRegisteredEvent(String name) {
        super(UUID.randomUUID().toString(), name);
        this.name = name;
    }

    @Override
    public LocalDateTime occurredOn() {
        return LocalDateTime.now();
    }
}
