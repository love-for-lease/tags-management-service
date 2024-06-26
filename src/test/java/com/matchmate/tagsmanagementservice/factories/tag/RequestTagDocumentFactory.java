package com.matchmate.tagsmanagementservice.factories.tag;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import org.instancio.Instancio;
import org.instancio.Model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.instancio.Select.field;

public class RequestTagDocumentFactory {

    private static final Model<RequestTagDocument> REQUEST_TAG_DOCUMENT_MODEL = Instancio.of(RequestTagDocument.class)
            .generate(field(RequestTagDocument::getRequests), gen -> gen.longs().range(0L, 20L))
            .set(field(RequestTagDocument::getRequestedAt), ZonedDateTime.now())
            .toModel();

    private static final Model<RequestTagDocument> REQUEST_TAG_DOCUMENT_MODEL_WITH_20_REQUESTS = Instancio.of(RequestTagDocument.class)
            .set(field(RequestTagDocument::getRequests), 20L)
            .set(field(RequestTagDocument::getRequestedAt), ZonedDateTime.now())
            .toModel();

    public static RequestTagDocument validWithName(String name) {
        return Instancio.of(REQUEST_TAG_DOCUMENT_MODEL)
                .set(field(RequestTagDocument::getName), name)
                .create();
    }
    public static RequestTagDocument validWithNameAndId(String name) {
        return Instancio.of(REQUEST_TAG_DOCUMENT_MODEL)
                .set(field(RequestTagDocument::getName), name)
                .set(field(RequestTagDocument::getId), UUID.randomUUID().toString())
                .create();
    }

    public static RequestTagDocument withDate(ZonedDateTime date) {
        return Instancio.of(REQUEST_TAG_DOCUMENT_MODEL)
                .set(field(RequestTagDocument::getRequestedAt), date)
                .create();
    }

    public static List<RequestTagDocument> withSizeAndDateWith20Request(int size, ZonedDateTime date) {
        return Instancio.ofList(REQUEST_TAG_DOCUMENT_MODEL_WITH_20_REQUESTS)
                .size(size)
                .set(field(RequestTagDocument::getRequestedAt), date)
                .create();
    }

    public static List<RequestTagDocument> withSizeWith20Request(int size) {
        return Instancio.ofList(REQUEST_TAG_DOCUMENT_MODEL_WITH_20_REQUESTS)
                .size(size)
                .create();
    }

}
