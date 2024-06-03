package com.matchmate.tagsmanagementservice.adapter.queues;

import com.matchmate.tagsmanagementservice.common.event.DomainEvent;

import java.util.List;

public interface QueueSender {
    void send(String queueName, DomainEvent payload);
    void send(String queueName, List<DomainEvent> payload);
}
