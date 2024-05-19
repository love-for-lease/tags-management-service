package com.matchmate.tagsmanagementservice;

import com.matchmate.tagsmanagementservice.common.event.DomainEvent;
import com.matchmate.tagsmanagementservice.common.event.DomainEventPublisher;
import com.matchmate.tagsmanagementservice.common.event.EventStore;
import com.matchmate.tagsmanagementservice.common.event.EventStoreMongoRepository;
import com.matchmate.tagsmanagementservice.common.event.StoredEvent;
import com.matchmate.tagsmanagementservice.domain.models.Tag;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

class TagRegisterTest {

    @Mock
    private EventStore eventStore;

    @Mock
    private EventStoreMongoRepository eventStoreRepository;

    private DomainEventPublisher domainEventPublisher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        domainEventPublisher = DomainEventPublisher.instance();
        ReflectionTestUtils.setField(domainEventPublisher, "eventStore", eventStore);
    }

    @Test
    @DisplayName("Should save events in event store When raise events")
    void raiseEvents_ShouldCallPublishAll_WithEventList() {
        Tag tag = new Tag();
        tag.register("Anime");
        var storedEvent = Instancio.createList(StoredEvent.class);

        when(eventStoreRepository.saveAll(storedEvent)).thenReturn(storedEvent);
        when(eventStore.append(tag.getEvents())).thenReturn(storedEvent);

        tag.raiseEvents();

        verify(eventStore, times(1)).append(tag.getEvents());
    }

    @Test
    void raiseEvents_ShouldCallPublishAll_WithSingleList() {
        Tag tag = new Tag();
        tag.register("Anime");
        StoredEvent storedEvent = Instancio.create(StoredEvent.class);
        DomainEvent aDomainEvent = tag.getEvents().get(0);

        when(eventStoreRepository.save(storedEvent)).thenReturn(storedEvent);
        when(eventStore.append(aDomainEvent)).thenReturn(storedEvent);

        tag.raiseEvent();

        verify(eventStore, times(1)).append(aDomainEvent);
    }

    @Test
    void publish_ShouldCallEventStoreAppend_WithSingleEvent() {
        Tag tag = new Tag();
        tag.register("Anime");
        tag.register("LOL");

        domainEventPublisher.publishAll(tag.getEvents());

        verify(eventStore, times(1)).append(tag.getEvents());
    }

    @Test
    void publishAll_ShouldCallEventStoreAppend_WithEventList() {
        Tag tag = new Tag();
        tag.register("Anime");
        tag.register("LOL");
        List<DomainEvent> events = Arrays.asList(tag.getEvents().get(0), tag.getEvents().get(1));

        domainEventPublisher.publishAll(events);

        verify(eventStore).append(events);
    }
}
