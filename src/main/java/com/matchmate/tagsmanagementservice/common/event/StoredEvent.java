package com.matchmate.tagsmanagementservice.common.event;

import com.matchmate.tagsmanagementservice.common.serializer.EventSerializer;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import static com.matchmate.tagsmanagementservice.common.AssertionConcern.assertArgumentLength;
import static com.matchmate.tagsmanagementservice.common.AssertionConcern.assertArgumentNotEmpty;

@Document("stored-event")
@Getter
public class StoredEvent {

    @Id
    private String eventId;
    private String eventBody;
    private LocalDateTime occurredOn;
    private String typeName;

    public StoredEvent(String aTypeName, LocalDateTime anOccurredOn, String anEventBody) {
        this();

        this.setEventBody(anEventBody);
        this.setOccurredOn(anOccurredOn);
        this.setTypeName(aTypeName);
    }

    public StoredEvent(String aTypeName, LocalDateTime anOccurredOn, String anEventBody, String anEventId) {
        this(aTypeName, anOccurredOn, anEventBody);

        this.setEventId(anEventId);
    }

    public StoredEvent() {
    }

    public String eventBody() {
        return this.eventBody;
    }

    public String eventId() {
        return this.eventId;
    }

    public LocalDateTime occurredOn() {
        return this.occurredOn;
    }

    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> T toDomainEvent() {
        Class<T> domainEventClass = null;

        try {
            domainEventClass = (Class<T>) Class.forName(this.typeName());
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Class load error, because: "
                            + e.getMessage());
        }

        T domainEvent =
                EventSerializer
                        .instance()
                        .deserialize(this.eventBody(), domainEventClass);

        return domainEvent;
    }

    public String typeName() {
        return this.typeName;
    }

    @Override
    public boolean equals(Object anObject) {
        boolean equalObjects = false;

        if (anObject != null && this.getClass() == anObject.getClass()) {
            StoredEvent typedObject = (StoredEvent) anObject;
            equalObjects = this.eventId() == typedObject.eventId();
        }

        return equalObjects;
    }

    @Override
    public String toString() {
        return "StoredEvent [eventBody=" + eventBody + ", eventId=" + eventId + ", occurredOn=" + occurredOn + ", typeName="
                + typeName + "]";
    }

    protected void setEventBody(String anEventBody) {
        assertArgumentNotEmpty(anEventBody, "The event body is required.");
        assertArgumentLength(anEventBody, 1, 65000, "The event body must be 65000 characters or less.");

        this.eventBody = anEventBody;
    }

    protected void setEventId(String anEventId) {
        this.eventId = anEventId;
    }

    protected void setOccurredOn(LocalDateTime anOccurredOn) {
        this.occurredOn = anOccurredOn;
    }

    protected void setTypeName(String aTypeName) {
        assertArgumentNotEmpty(aTypeName, "The event type name is required.");
        assertArgumentLength(aTypeName, 1, 100, "The event type name must be 100 characters or less.");

        this.typeName = aTypeName;
    }
}