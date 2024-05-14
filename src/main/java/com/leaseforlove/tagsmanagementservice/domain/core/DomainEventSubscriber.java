package com.leaseforlove.tagsmanagementservice.domain.core;

public interface DomainEventSubscriber<T> {

    public void handleEvent(final T aDomainEvent);

    public Class<T> subscribedToEventType();
}