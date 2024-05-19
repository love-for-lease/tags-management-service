package com.matchmate.tagsmanagementservice.common.event;

import java.util.List;

public interface DomainEventHandler<T extends DomainEvent> {
    void handleEvent(final List<T> aDomainEvent);
}
