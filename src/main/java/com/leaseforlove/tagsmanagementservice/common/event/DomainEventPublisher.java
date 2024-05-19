package com.leaseforlove.tagsmanagementservice.common.event;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DomainEventPublisher {

    @Autowired
    private EventStore eventStore;

    private static final ThreadLocal<DomainEventPublisher> instance = new ThreadLocal<>() {
        protected DomainEventPublisher initialValue() {
            return new DomainEventPublisher();
        }
    };

    public static DomainEventPublisher instance() {
        return instance.get();
    }

    public void publish(final DomainEvent aDomainEvent) {
        eventStore.append(aDomainEvent);
    }

    public void publishAll(List<DomainEvent> aDomainEvents) {
        eventStore.append(aDomainEvents);
    }
}
