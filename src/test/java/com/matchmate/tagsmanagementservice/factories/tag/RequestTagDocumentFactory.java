package com.matchmate.tagsmanagementservice.factories.tag;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import org.instancio.Instancio;
import org.instancio.Model;
import static org.instancio.Select.field;

import java.time.OffsetDateTime;

public class RequestTagDocumentFactory {

    private static final Model<RequestTagDocument> REQUEST_TAG_DOCUMENT_MODEL = Instancio.of(RequestTagDocument.class)
            .generate(field(RequestTagDocument::getRequests), gen -> gen.longs().range(0L, 20L))
            .set(field(RequestTagDocument::getRequestedAt), OffsetDateTime.now())
            .toModel();

    public static RequestTagDocument validRequestTagDocumentWithName(String name) {
        return Instancio.of(REQUEST_TAG_DOCUMENT_MODEL)
                .set(field(RequestTagDocument::getName), name)
                .create();
    }
}
