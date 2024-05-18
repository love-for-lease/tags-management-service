package com.leaseforlove.tagsmanagementservice.common.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventStoreImpl implements EventStore {
    private final EventStoreMongoRepository eventStoreMongoRepository;

    @Override
    public StoredEvent append(DomainEvent aDomainEvent) {
        String eventSerialization =
                EventSerializer.instance().serialize(aDomainEvent);

        StoredEvent storedEvent = new StoredEvent(
                aDomainEvent.getClass().getName(),
                aDomainEvent.occurredOn(),
                eventSerialization
        );

        eventStoreMongoRepository.save(storedEvent);
        return storedEvent;
    }
}
