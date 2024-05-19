package com.leaseforlove.tagsmanagementservice.common.event;

import java.time.LocalDateTime;


public interface DomainEvent {
    LocalDateTime occurredOn();
}
