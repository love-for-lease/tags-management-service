package com.matchmate.tagsmanagementservice.common.domain;

import com.matchmate.tagsmanagementservice.common.event.DomainEvent;
import com.matchmate.tagsmanagementservice.common.event.DomainEventPublisher;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Aggregate {

    private final List<DomainEvent> events = new ArrayList<>();

    public void raiseEvents() {
        DomainEventPublisher.instance().publishAll(this.events);
    }

    public void raiseEvent() {
        DomainEventPublisher.instance().publish(this.events.stream().findFirst().get());
    }

    public void clearEvents() {
        this.events.clear();
    }
}