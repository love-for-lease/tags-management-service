package com.matchmate.tagsmanagementservice.common.domain;

import com.matchmate.tagsmanagementservice.common.observer.Observable;
import lombok.Getter;

@Getter
public abstract class Aggregate<ID extends Identifier> extends Observable {

    private final ID id;

    protected Aggregate(ID id) {
        super();
        this.id = id;
    }

//    public void raiseEvents() {
//        DomainEventPublisher.instance().publishAll(Optional.of(this.events)
//                .orElseThrow(() -> new RuntimeException("not have events")));
//        this.clearEvents();
//    }
//
//    public void raiseEvent() {
//        DomainEventPublisher.instance().publish(this.events.stream().findFirst()
//                .orElseThrow(() -> new RuntimeException("not have events")));
//        this.clearEvents();
//    }
//
//    public void clearEvents() {
//        this.events.clear();
//    }
}
