package com.leaseforlove.tagsmanagementservice.common.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventStoreImpl implements EventStore {

    private EventStoreMongoRepository eventStoreMongoRepository;

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

    @Override
    public List<StoredEvent> append(List<DomainEvent> aDomainEvent) {
        String eventsSerialization =
                EventSerializer.instance().serialize(aDomainEvent);

        List<StoredEvent> storedEventsToPersistence = aDomainEvent.stream().map(event -> new StoredEvent(
                        event.getClass().getName(),
                        event.occurredOn(),
                        eventsSerialization))
                .toList();

        return eventStoreMongoRepository.saveAll(storedEventsToPersistence);
    }
}
