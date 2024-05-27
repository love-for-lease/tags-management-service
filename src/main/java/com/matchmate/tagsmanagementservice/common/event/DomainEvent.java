package com.matchmate.tagsmanagementservice.common.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public abstract class DomainEvent extends ApplicationEvent {
    private final String id;
    public abstract LocalDateTime occurredOn();

    protected DomainEvent(String id, String payload) {
        super(payload);
        this.id = id;
    }
}
