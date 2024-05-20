package com.matchmate.tagsmanagementservice.common.event;

import com.matchmate.tagsmanagementservice.domain.events.TagRegisteredEvent;
import com.matchmate.tagsmanagementservice.factories.common.event.StoredEventFactory;
import com.matchmate.tagsmanagementservice.factories.domain.events.TagRegisteredEventFactory;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import java.util.List;

class EventStoreImplTest {

    @InjectMocks
    private EventStoreImpl eventStore;

    @Mock
    private EventStoreMongoRepository eventStoreRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void append_ShouldStoreTagRegisteredEvent_AndVerifyEventBodyContainsTagName() {
        var tagName = "gymrat";
        TagRegisteredEvent tagRegisteredEvent = TagRegisteredEventFactory.validWithName(tagName);
        var storedEventStub = StoredEventFactory.create(tagRegisteredEvent);
        ArgumentCaptor<StoredEvent> storedEventCaptor = ArgumentCaptor.forClass(StoredEvent.class);

        when(eventStoreRepository.save(storedEventCaptor.capture())).thenReturn(storedEventStub);
        eventStore.append(tagRegisteredEvent);
        var storedEventValue = storedEventCaptor.getValue();

        verify(eventStoreRepository, times(1)).save(storedEventValue);
        Assertions.assertThat(storedEventValue.getEventBody().contains(tagName));
        assertNotNull(storedEventValue.eventId());
    }

    @Test
    void append_ShouldStoreMultipleTagRegisteredEvents_AndVerifyEachStoredEventHasEventId() {
        List<TagRegisteredEvent> events = TagRegisteredEventFactory.validListWithSize(3);
        List<StoredEvent> storedEventsStub = StoredEventFactory.createList(events);

        var eventsCaptor = ArgumentCaptor.forClass(List.class);
        when(eventStoreRepository.saveAll(eventsCaptor.capture())).thenReturn(storedEventsStub);
        eventStore.append(events);

        List<StoredEvent> storedEventsValue = eventsCaptor.getValue();
        verify(eventStoreRepository, times(1)).saveAll(storedEventsValue);
        storedEventsValue.forEach(e -> assertNotNull(e.getEventId()));
    }
}
