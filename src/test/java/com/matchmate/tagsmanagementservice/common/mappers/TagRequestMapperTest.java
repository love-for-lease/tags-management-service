package com.matchmate.tagsmanagementservice.common.mappers;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;
import com.matchmate.tagsmanagementservice.domain.models.tagrequest.TagRequest;
import com.matchmate.tagsmanagementservice.factories.tag.AvailableTagDocumentFactory;
import com.matchmate.tagsmanagementservice.factories.tag.RequestTagDocumentFactory;
import com.matchmate.tagsmanagementservice.factories.tag.TagRequestFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagRequestMapperTest {

    @Test
    void tagRequestToDocument_ShouldStoreTagRegisteredEvent_AndVerifyEventBodyContainsTagName() {

        TagRequest tagRequest = TagRequestFactory.withValidName("TEST_TAG");

        RequestTagDocument result = TagRequestMapper.tagRequestToDocument(tagRequest);

        assertEquals(tagRequest.getName(), result.getName());
        assertEquals(tagRequest.getRequests(), result.getRequests());
        assertEquals(tagRequest.getRequestedAt(), result.getRequestedAt());
    }

    @Test
    void documentToTagRequest_ShouldStoreTagRegisteredEvent_AndVerifyEventBodyContainsTagName() {

        RequestTagDocument tagDocument = RequestTagDocumentFactory.validWithName("TEST_TAG");

        TagRequest result = TagRequestMapper.documentToTagRequest(tagDocument);

        assertEquals(tagDocument.getName(), result.getName());
        assertEquals(tagDocument.getRequests(), result.getRequests());
        assertEquals(tagDocument.getRequestedAt(), result.getRequestedAt());
    }

    @Test
    void documentToRequest_ShouldStoreTagRegisteredEvent_AndVerifyEventBodyContainsTagName() {
        AvailableTagDocument availableTagDocument = AvailableTagDocumentFactory.validWithName("TEST_TAG");

        TagAvailable result = TagRequestMapper.documentToRequest(availableTagDocument);

        assertEquals(availableTagDocument.getName(), result.getName());
        assertEquals(availableTagDocument.getStatus(), result.getStatus());
    }
}