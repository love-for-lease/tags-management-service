package com.matchmate.tagsmanagementservice.factories.tag;

import com.matchmate.tagsmanagementservice.adapter.queues.messages.RequestTagMessage;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.List;

import static org.instancio.Select.field;

public class RequestTagMessageFactory {
    private static final Model<RequestTagMessage> REQUEST_TAG_MESSAGE_MODEL =
            Instancio.of(RequestTagMessage.class)
                    .toModel();

    public static RequestTagMessage withValidName(String name) {
        return Instancio.of(REQUEST_TAG_MESSAGE_MODEL)
                .set(field(RequestTagMessage::name), name)
                .create();
    }

    public static List<RequestTagMessage> withSize(int size) {
        return Instancio.ofList(REQUEST_TAG_MESSAGE_MODEL)
                .size(size)
                .create();
    }
}
