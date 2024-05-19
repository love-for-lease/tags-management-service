package com.leaseforlove.tagsmanagementservice.common.event;

public interface EventStore {
    StoredEvent append(DomainEvent aDomainEvent);
}
