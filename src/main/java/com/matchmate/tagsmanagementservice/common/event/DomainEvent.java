package com.matchmate.tagsmanagementservice.common.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public abstract class DomainEvent {
    private final String id;
    private final String name;
    public abstract LocalDateTime occurredOn();

    protected DomainEvent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DomainEvent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
