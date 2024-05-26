package com.matchmate.tagsmanagementservice.adapter.queues;

import com.matchmate.tagsmanagementservice.common.event.DomainEvent;

import java.util.List;

public interface QueueSender<T extends DomainEvent> {
    void send(String queueName, T payload);
    void send(String queueName, List<T> payload);
}
