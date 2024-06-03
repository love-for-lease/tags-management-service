package com.matchmate.tagsmanagementservice.common.event;

import java.util.List;

public interface EventStore {
    StoredEvent append(DomainEvent aDomainEvent);
    List<StoredEvent> append(List<? extends DomainEvent> aDomainEvent);
}
