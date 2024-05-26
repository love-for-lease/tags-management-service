package com.matchmate.tagsmanagementservice.adapter.handlers;

import com.matchmate.tagsmanagementservice.adapter.queues.QueueSender;
import com.matchmate.tagsmanagementservice.application.properties.QueueProperties;
import com.matchmate.tagsmanagementservice.common.event.DomainEventHandler;
import com.matchmate.tagsmanagementservice.domain.events.TagRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TagsRegisteredEventHandler implements DomainEventHandler<TagRegisteredEvent> {
    private final QueueSender queueSender;
    private final QueueProperties queueProperties;

    @Override
    public void handleEvent(List<TagRegisteredEvent> aDomainEvent) {
        queueSender.send(queueProperties.getTagRegisteredQueue(), aDomainEvent);
    }
}
