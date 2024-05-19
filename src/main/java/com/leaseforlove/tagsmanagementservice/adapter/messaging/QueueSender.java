package com.leaseforlove.tagsmanagementservice.adapter.messaging;

import com.leaseforlove.tagsmanagementservice.common.event.DomainEvent;

import java.util.List;

public interface QueueSender<T extends DomainEvent> {
    void send(String queueName, T payload);
    void send(String queueName, List<T> payload);
}
