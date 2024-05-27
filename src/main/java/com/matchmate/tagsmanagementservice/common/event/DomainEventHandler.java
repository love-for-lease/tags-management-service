package com.matchmate.tagsmanagementservice.common.event;

public interface DomainEventHandler<T extends DomainEvent> {
    void handleEvent(final T aDomainEvent);
}
