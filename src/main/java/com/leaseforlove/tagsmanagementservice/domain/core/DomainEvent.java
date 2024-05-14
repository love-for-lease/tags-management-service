package com.leaseforlove.tagsmanagementservice.domain.core;

import java.time.Instant;

public interface DomainEvent {
    public Instant occurredOn();
}
