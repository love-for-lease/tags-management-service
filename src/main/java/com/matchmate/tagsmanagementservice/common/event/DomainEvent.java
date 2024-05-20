package com.matchmate.tagsmanagementservice.common.event;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class DomainEvent {
    private final String id;
    public abstract LocalDateTime occurredOn();

    protected DomainEvent(String id) {
        this.id = id;
    }
}
