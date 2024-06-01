package com.matchmate.tagsmanagementservice.common.mappers;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;
import com.matchmate.tagsmanagementservice.domain.models.tagrequest.RequestTag;
import com.matchmate.tagsmanagementservice.factories.tag.AvailableTagDocumentFactory;
import com.matchmate.tagsmanagementservice.factories.tag.RequestTagDocumentFactory;
import com.matchmate.tagsmanagementservice.factories.tag.TagRequestFactory;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestTagMapperTest {

    @Test
    void tagRequestToDocument_ShouldStoreTagRegisteredEvent_AndVerifyEventBodyContainsTagName() {

        RequestTag requestTag = TagRequestFactory.withValidName("TEST_TAG");

        RequestTagDocument result = RequestTagMapper.toDocument(requestTag, ZonedDateTime.now());

        assertEquals(requestTag.getName(), result.getName());
        assertEquals(requestTag.getId().fromValue(), result.getId());
        assertEquals(requestTag.getRequests(), result.getRequests());
    }

    @Test
    void documentToTagRequest_ShouldStoreTagRegisteredEvent_AndVerifyEventBodyContainsTagName() {

        RequestTagDocument tagDocument = RequestTagDocumentFactory.validWithNameAndId("TEST_TAG");

        RequestTag result = RequestTagMapper.toDomain(tagDocument);

        assertEquals(tagDocument.getName(), result.getName());
        assertEquals(tagDocument.getRequests(), result.getRequests());
    }

    @Test
    void documentToRequest_ShouldStoreTagRegisteredEvent_AndVerifyEventBodyContainsTagName() {
        AvailableTagDocument availableTagDocument = AvailableTagDocumentFactory.validWithName("TEST_TAG");

        TagAvailable result = RequestTagMapper.documentToRequest(availableTagDocument);

        assertEquals(availableTagDocument.getName(), result.getName());
        assertEquals(availableTagDocument.getStatus(), result.getStatus());
    }
}