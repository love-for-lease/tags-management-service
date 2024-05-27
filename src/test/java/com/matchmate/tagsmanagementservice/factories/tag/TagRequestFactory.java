package com.matchmate.tagsmanagementservice.factories.tag;

import com.matchmate.tagsmanagementservice.domain.models.tagrequest.RequestTag;
import org.instancio.Instancio;
import org.instancio.Model;

import java.time.OffsetDateTime;
import java.util.List;

import static org.instancio.Select.field;

public class TagRequestFactory {

    private static final Model<RequestTag> TAG_REQUEST_MODEL = Instancio.of(RequestTag.class)
            .generate(field(RequestTag::getRequests), gen -> gen.longs().range(0L, 20L))
            .toModel();

    public static RequestTag withValidName(String name) {
        return Instancio.of(TAG_REQUEST_MODEL)
                .set(field(RequestTag::getName), name)
                .create();
    }

    public static List<RequestTag> withSizeAndDate(int size, OffsetDateTime date) {
        return Instancio.ofList(TAG_REQUEST_MODEL)
                .size(size)
                .create();
    }

    public static RequestTag withNameAndRequest(String name, Long requests) {
        return Instancio.of(TAG_REQUEST_MODEL)
                .set(field(RequestTag::getName), name)
                .set(field(RequestTag::getRequests), requests)
                .create();
    }
}
