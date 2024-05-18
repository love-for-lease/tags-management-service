package com.leaseforlove.tagsmanagementservice.adapter.handlers;

import com.leaseforlove.tagsmanagementservice.adapter.messaging.QueueSender;
import com.leaseforlove.tagsmanagementservice.application.config.QueuePropertiesConfig;
import com.leaseforlove.tagsmanagementservice.common.event.DomainEventHandler;
import com.leaseforlove.tagsmanagementservice.common.serializer.ObjectSerializer;
import com.leaseforlove.tagsmanagementservice.domain.events.TagRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TagsRegisteredEventHandler implements DomainEventHandler<TagRegisteredEvent> {
    private final QueueSender queueSender;
    private final QueuePropertiesConfig queueProperties;

    @Override
    public void handleEvent(List<TagRegisteredEvent> aDomainEvent) {
        queueSender.send(queueProperties.getTagsRegisteredQueue(),
                ObjectSerializer.instance().serialize(aDomainEvent));
    }
}
