package com.leaseforlove.tagsmanagementservice.domain.events;

import com.leaseforlove.tagsmanagementservice.common.event.DomainEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class TagRegisteredEvent extends DomainEvent {
    private final String id;
    private final String name;

    public TagRegisteredEvent(String name) {
        super();
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public LocalDateTime occurredOn() {
        return LocalDateTime.now();
    }
}
