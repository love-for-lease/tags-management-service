package com.matchmate.tagsmanagementservice.common.event;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DomainEventPublisher implements ApplicationEventPublisherAware {

    @Autowired
    private EventStore eventStore;

    @Autowired
     private ApplicationEventPublisher applicationEventPublisher;

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
        applicationEventPublisher.publishEvent(aDomainEvent);
    }

    public void publishAll(List<DomainEvent> aDomainEvents) {
        eventStore.append(aDomainEvents);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
