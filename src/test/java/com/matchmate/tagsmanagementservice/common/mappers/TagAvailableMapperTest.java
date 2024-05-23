package com.matchmate.tagsmanagementservice.common.mappers;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;
import com.matchmate.tagsmanagementservice.factories.tag.AvailableTagDocumentFactory;
import com.matchmate.tagsmanagementservice.factories.tag.RequestTagDocumentFactory;
import com.matchmate.tagsmanagementservice.factories.tag.TagAvailableFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagAvailableMapperTest {

    @Test
    void tagAvailableToDocument_ShouldMapTagAvailableToDocument_AndVerifyDocumentFields() {
        TagAvailable tagAvailable = TagAvailableFactory.withValidName("TEST_TAG");

        AvailableTagDocument result = TagAvailableMapper.tagAvailableToDocument(tagAvailable);

        assertEquals(tagAvailable.getName(), result.getName());
        assertEquals(tagAvailable.getStatus(), result.getStatus());
    }

    @Test
    void documentToTagAvailable_ShouldMapDocumentToTagAvailable_AndVerifyTagAvailableFields() {
        AvailableTagDocument tagDocument = AvailableTagDocumentFactory.validWithName("TEST_TAG");

        TagAvailable result = TagAvailableMapper.documentToTagAvailable(tagDocument);

        assertEquals(tagDocument.getName(), result.getName());
        assertEquals(tagDocument.getStatus(), result.getStatus());
    }

    @Test
    void requestToTagAvailable_ShouldMapRequestToTagAvailable_AndVerifyTagAvailableFields() {
        RequestTagDocument requestTag = RequestTagDocumentFactory.validWithName("TEST_TAG");


        TagAvailable result = TagAvailableMapper.requestToTagAvailable(requestTag);

        assertEquals(requestTag.getName(), result.getName());
    }
}