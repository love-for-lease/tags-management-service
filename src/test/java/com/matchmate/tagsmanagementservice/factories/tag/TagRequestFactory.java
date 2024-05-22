package com.matchmate.tagsmanagementservice.factories.tag;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.domain.models.TagRequest;
import org.instancio.Instancio;
import org.instancio.Model;

import java.time.OffsetDateTime;
import java.util.List;

import static org.instancio.Select.field;

public class TagRequestFactory {

    private static final Model<TagRequest> TAG_RAQUEST_MODEL = Instancio.of(TagRequest.class)
            .generate(field(TagRequest::getRequests), gen -> gen.longs().range(0L, 20L))
            .set(field(TagRequest::getRequestedAt), OffsetDateTime.now())
            .toModel();

    public static List<TagRequest> withSizeAndDate(int size, OffsetDateTime date) {
        return Instancio.ofList(TAG_RAQUEST_MODEL)
                .size(size)
                .set(field(TagRequest::getRequestedAt), date)
                .create();
    }
}
