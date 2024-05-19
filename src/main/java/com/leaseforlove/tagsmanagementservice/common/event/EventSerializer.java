package com.leaseforlove.tagsmanagementservice.common.event;

import com.leaseforlove.tagsmanagementservice.common.serializer.AbstractSerializer;

import java.util.List;

public class EventSerializer extends AbstractSerializer {

    private static EventSerializer eventSerializer;

    public static synchronized EventSerializer instance() {
        if (EventSerializer.eventSerializer == null) {
            EventSerializer.eventSerializer = new EventSerializer();
        }

        return EventSerializer.eventSerializer;
    }

    public EventSerializer(boolean isCompact) {
        this(false, isCompact);
    }

    public EventSerializer(boolean isPretty, boolean isCompact) {
        super(isPretty, isCompact);
    }

    public String serialize(DomainEvent aDomainEvent) {

        return this.gson().toJson(aDomainEvent);
    }

    public <T extends DomainEvent> T deserialize(String aSerialization, final Class<T> aType) {
        T domainEvent = this.gson().fromJson(aSerialization, aType);

        return domainEvent;
    }

    private EventSerializer() {
        this(false, false);
    }

    public String serialize(List<DomainEvent> aDomainEvent) {
        return this.gson().toJson(aDomainEvent);
    }
}
