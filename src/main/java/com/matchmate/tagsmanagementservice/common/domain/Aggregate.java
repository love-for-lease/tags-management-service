package com.matchmate.tagsmanagementservice.common.domain;

import com.matchmate.tagsmanagementservice.common.event.DomainEvent;
import com.matchmate.tagsmanagementservice.common.event.DomainEventPublisher;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public abstract class Aggregate<ID extends Identifier> {

    private ID id;
    private final List<DomainEvent> events = new ArrayList<>();

    public void raiseEvents() {
        DomainEventPublisher.instance().publishAll(Optional.of(this.events)
                .orElseThrow(() -> new RuntimeException("not have events")));
        this.clearEvents();
    }

    public void raiseEvent() {
        DomainEventPublisher.instance().publish(this.events.stream().findFirst()
                .orElseThrow(() -> new RuntimeException("not have events")));
        this.clearEvents();
    }

    public void clearEvents() {
        this.events.clear();
    }
}
