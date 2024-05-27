package com.matchmate.tagsmanagementservice;

import com.matchmate.tagsmanagementservice.common.event.DomainEvent;
import com.matchmate.tagsmanagementservice.common.event.DomainEventPublisher;
import com.matchmate.tagsmanagementservice.common.event.EventStore;
import com.matchmate.tagsmanagementservice.common.event.EventStoreMongoRepository;
import com.matchmate.tagsmanagementservice.common.event.StoredEvent;
import com.matchmate.tagsmanagementservice.domain.models.tag.Tag;
import com.matchmate.tagsmanagementservice.domain.models.tag.TagId;
import org.instancio.Instancio;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

class TagRegisterTest {

    @Mock
    private EventStore eventStore;

    @Mock
    private EventStoreMongoRepository eventStoreRepository;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    private DomainEventPublisher domainEventPublisher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        domainEventPublisher = DomainEventPublisher.instance();
        ReflectionTestUtils.setField(domainEventPublisher, "eventStore", eventStore);
        ReflectionTestUtils.setField(domainEventPublisher, "applicationEventPublisher", applicationEventPublisher);
    }

    @Test
    @DisplayName("Should save events in event store When raise events")
    void raiseEvents_ShouldCallPublishAll_WithEventList() {
        Tag tag = new Tag("Anime");
        tag.register();
        var storedEvent = Instancio.createList(StoredEvent.class);

        when(eventStoreRepository.saveAll(storedEvent)).thenReturn(storedEvent);
        when(eventStore.append(tag.getEvents())).thenReturn(storedEvent);

        tag.raiseEvents();

        verify(eventStore, times(1)).append(tag.getEvents());
    }

    @Test
    void raiseEvents_ShouldCallPublishAll_WithSingleList() {
        Tag tag = new Tag("Anime");
        tag.register();
        StoredEvent storedEvent = Instancio.create(StoredEvent.class);
        DomainEvent aDomainEvent = tag.getEvents().get(0);

        doNothing().when(applicationEventPublisher).publishEvent(aDomainEvent);
        when(eventStoreRepository.save(storedEvent)).thenReturn(storedEvent);
        when(eventStore.append(aDomainEvent)).thenReturn(storedEvent);

        tag.raiseEvent();

        verify(eventStore, times(1)).append(aDomainEvent);
    }

    @Test
    void publish_ShouldCallEventStoreAppend_WithSingleEvent() {
        Tag tag = new Tag("Anime");

        domainEventPublisher.publishAll(tag.getEvents());

        verify(eventStore, times(1)).append(tag.getEvents());
    }

    @Test
    void publishAll_ShouldCallEventStoreAppend_WithEventList() {
        Tag tag = new Tag("LOL");
        tag.register();
        List<DomainEvent> events = Collections.singletonList(tag.getEvents().get(0));

        domainEventPublisher.publishAll(events);

        verify(eventStore).append(events);
    }

    @Test
    void registerTag_ShouldAssignNonNullId_OfTypeTagId() {
        Tag tag = new Tag("one piece");
        tag.register();

        assertNotNull(tag.getId().fromValue());
        assertEquals(tag.getId().getClass(), TagId.class);
    }
}
