package com.leaseforlove.tagsmanagementservice.common.event;

import java.util.List;

public interface EventStore {
    StoredEvent append(DomainEvent aDomainEvent);

    List<StoredEvent> append(List<DomainEvent> aDomainEvent);
}
