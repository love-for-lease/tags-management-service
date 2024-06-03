package com.matchmate.tagsmanagementservice.common.event;

import com.matchmate.tagsmanagementservice.common.serializer.EventSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EventStoreImpl implements EventStore {

    private final EventStoreMongoRepository eventStoreRepository;

    @Override
    public StoredEvent append(DomainEvent aDomainEvent) {
        String eventSerialization =
                EventSerializer.instance().serialize(aDomainEvent);

        StoredEvent storedEvent = new StoredEvent(
                aDomainEvent.getClass().getName(),
                aDomainEvent.occurredOn(),
                eventSerialization,
                aDomainEvent.getId()
        );

        return eventStoreRepository.save(storedEvent);
    }

    @Override
    public List<StoredEvent> append(List<? extends DomainEvent> aDomainEvent) {
        String eventsSerialization =
                EventSerializer.instance().serialize(Collections.unmodifiableList(aDomainEvent));

        List<StoredEvent> storedEventsToPersistence = aDomainEvent.stream().map(event -> new StoredEvent(
                        event.getClass().getName(),
                        event.occurredOn(),
                        eventsSerialization,
                        event.getId()))
                .toList();

        return eventStoreRepository.saveAll(storedEventsToPersistence);
    }
}
