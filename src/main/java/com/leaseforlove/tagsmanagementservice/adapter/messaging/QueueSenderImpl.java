package com.leaseforlove.tagsmanagementservice.adapter.messaging;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueSenderImpl implements QueueSender{
    private final SqsTemplate sqsTemplate;

    @Override
    public void send(String queueName, String payload) {
        sqsTemplate.send(queueName, payload);
    }
}
