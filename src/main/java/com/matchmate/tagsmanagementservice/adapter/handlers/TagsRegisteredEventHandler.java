package com.matchmate.tagsmanagementservice.adapter.handlers;

import com.matchmate.tagsmanagementservice.adapter.queues.QueueSender;
import com.matchmate.tagsmanagementservice.application.properties.QueueProperties;
import com.matchmate.tagsmanagementservice.common.event.DomainEventHandler;
import com.matchmate.tagsmanagementservice.common.event.EventStore;
import com.matchmate.tagsmanagementservice.domain.events.TagRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class TagsRegisteredEventHandler implements DomainEventHandler<TagRegisteredEvent> {
    private final QueueSender queueSender;
    private final QueueProperties queueProperties;
    private final EventStore eventStore;

    @Override
    @Async
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleEvent(TagRegisteredEvent aDomainEvent) {
        try {
            log.info("Enviando evento para a fila. {}", aDomainEvent.toString());
            queueSender.send(queueProperties.getTagRegisteredQueue(), aDomainEvent);

            log.info("Saving event: {} in event store", eventStore);
            eventStore.append(aDomainEvent);
        } catch (Exception ex) {
            // TODO implementar trativa caso ocorra um erro no registro
        }
    }
}
