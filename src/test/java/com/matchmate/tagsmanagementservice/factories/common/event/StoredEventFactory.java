package com.matchmate.tagsmanagementservice.factories.common.event;

import com.matchmate.tagsmanagementservice.common.event.DomainEvent;
import com.matchmate.tagsmanagementservice.common.event.StoredEvent;
import com.matchmate.tagsmanagementservice.common.serializer.EventSerializer;
import org.instancio.Instancio;
import static org.instancio.Select.field;

import java.util.List;

public class StoredEventFactory {
    public static StoredEvent create(DomainEvent aDomainEvent) {
        return Instancio.of(StoredEvent.class)
                .set(field(StoredEvent::getTypeName), aDomainEvent.getClass().getName())
                .set(field(StoredEvent::getOccurredOn), aDomainEvent.occurredOn())
                .set(field(StoredEvent::getEventBody), EventSerializer.instance().serialize(aDomainEvent))
                .create();
    }

    public static List<StoredEvent> createList(List<? extends DomainEvent> domainEvents) {
        return domainEvents.stream().map(StoredEventFactory::create).toList();
    }
}
