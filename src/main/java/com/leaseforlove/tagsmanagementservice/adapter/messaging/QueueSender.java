package com.leaseforlove.tagsmanagementservice.adapter.messaging;

public interface QueueSender {
    void send(String queueName, String payload);
}
