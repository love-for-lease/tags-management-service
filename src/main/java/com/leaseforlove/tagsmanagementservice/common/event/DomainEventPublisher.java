package com.leaseforlove.tagsmanagementservice.common.event;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DomainEventPublisher {

    @Autowired
    private DomainEventHandler domainEventHandler;

    private static final ThreadLocal<DomainEventPublisher> instance = new ThreadLocal<>() {
        private DomainEventPublisher initValue() {
            return new DomainEventPublisher();
        }
    };

    public static DomainEventPublisher instance() {
        return instance.get();
    }

    public <T> void publish(final List<T> aDomainEvent) {
        domainEventHandler.handleEvent(aDomainEvent);
    }
}
