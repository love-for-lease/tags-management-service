package com.matchmate.tagsmanagementservice.common.mappers;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;
import com.matchmate.tagsmanagementservice.domain.models.tagrequest.TagRequest;

public class TagRequestMapper {


    public static RequestTagDocument tagRequestToDocument(TagRequest tagRequest) {

        return new RequestTagDocument(tagRequest.getName(),
                tagRequest.getRequests(),
                tagRequest.getRequestedAt());
    }

    public static TagRequest documentToTagRequest(RequestTagDocument tagDocument) {
        return TagRequest
                .builder()
                .name(tagDocument.getName())
                .requests(tagDocument.getRequests())
                .requestedAt(tagDocument.getRequestedAt())
                .build();
    }

    public static TagAvailable documentToRequest(AvailableTagDocument availableTagDocument) {
        return TagAvailable
                .builder()
                .name(availableTagDocument.getName())
                .status(availableTagDocument.getStatus())
                .build();
    }

}
