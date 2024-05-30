package com.matchmate.tagsmanagementservice.common.mappers;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;
import com.matchmate.tagsmanagementservice.domain.models.tagrequest.RequestTag;

import java.time.OffsetDateTime;

public class RequestTagMapper {

    private RequestTagMapper() {
    }

    public static RequestTagDocument toDocument(RequestTag requestTag, OffsetDateTime requestAt) {

        return new RequestTagDocument(
                requestTag.getId().fromValue(),
                requestTag.getName(),
                requestTag.getRequests(),
                requestAt);
    }

    public static RequestTagDocument toDocumentWithoutRequestAt(RequestTag requestTag) {

        return new RequestTagDocument(
                requestTag.getId().fromValue(),
                requestTag.getName(),
                requestTag.getRequests(),
                OffsetDateTime.now()
        );
    }

    public static RequestTag toDomain(RequestTagDocument tagDocument) {
        return new RequestTag(tagDocument.getId(), tagDocument.getName(), tagDocument.getRequests());
    }

    public static TagAvailable documentToRequest(AvailableTagDocument availableTagDocument) {
        return new TagAvailable(availableTagDocument.getName(),  availableTagDocument.getStatus());
    }
}
