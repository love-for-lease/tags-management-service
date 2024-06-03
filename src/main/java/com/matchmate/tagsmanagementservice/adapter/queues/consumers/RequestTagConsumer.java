package com.matchmate.tagsmanagementservice.adapter.queues.consumers;

import com.matchmate.tagsmanagementservice.adapter.handlers.RegisterRequestTagConsumerHandler;
import com.matchmate.tagsmanagementservice.adapter.queues.messages.RequestTagMessage;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestTagConsumer {

    private final RegisterRequestTagConsumerHandler registerRequestTagConsumerHandler;

    @SqsListener("${app.events.queues.tag-request-queue}")
    public void receiveRequestTag(RequestTagMessage requestTagMessage, @Headers MessageHeaders headers) {
        log.info("Received message: {}, messageId: {}", requestTagMessage.name(), headers.get(MessageHeaders.ID));
        registerRequestTagConsumerHandler.handler(requestTagMessage.name());
    }
}
