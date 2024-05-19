package com.matchmate.tagsmanagementservice.adapter.messaging;

import com.matchmate.tagsmanagementservice.common.event.DomainEvent;
import com.matchmate.tagsmanagementservice.common.serializer.ObjectSerializer;
import io.awspring.cloud.sqs.operations.SendResult;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("ALL")
@Component
@RequiredArgsConstructor
@Slf4j
public class QueueSenderImpl implements QueueSender {

    private final SqsTemplate sqsTemplate;

    @Override
    public void send(String queueName, DomainEvent payload) {
        try {
            SendResult<String> result = sqsTemplate.send(to -> to.queue(queueName)
                    .payload(ObjectSerializer.instance().serialize(payload))
                    .delaySeconds(10));
            log.info(String.format("MessageId: %s", result.messageId()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void send(String queueName, List payload) {
        payload.forEach(content -> this.send(queueName, (DomainEvent) content));
    }
}
