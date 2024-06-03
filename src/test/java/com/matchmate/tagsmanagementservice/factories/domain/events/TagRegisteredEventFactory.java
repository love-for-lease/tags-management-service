package com.matchmate.tagsmanagementservice.factories.domain.events;

import com.matchmate.tagsmanagementservice.domain.events.TagRegisteredEvent;
import org.instancio.Instancio;
import org.instancio.Model;
import static org.instancio.Select.field;

import java.util.List;
import java.util.UUID;

public class TagRegisteredEventFactory {
    private static final Model<TagRegisteredEvent> TAG_REGISTERED_EVENT_MODEL = Instancio.of(TagRegisteredEvent.class)
            .set(field(TagRegisteredEvent::getId), UUID.randomUUID().toString())
            .generate(field(TagRegisteredEvent::getTagName), gen -> gen.oneOf("pizza", "anime", "tecnologia"))
            .toModel();

    public static TagRegisteredEvent valid() {
        return Instancio.of(TAG_REGISTERED_EVENT_MODEL).create();
    }

    public static TagRegisteredEvent validWithName(String name) {
        return Instancio.of(TAG_REGISTERED_EVENT_MODEL)
                .set(field(TagRegisteredEvent::getTagName), name)
                .create();
    }

    public static List<TagRegisteredEvent> validListWithSize(int size) {
        return Instancio.ofList(TAG_REGISTERED_EVENT_MODEL).size(size).create();
    }
}
