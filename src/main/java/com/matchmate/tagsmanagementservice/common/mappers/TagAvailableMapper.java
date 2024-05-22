package com.matchmate.tagsmanagementservice.common.mappers;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;

public class TagAvailableMapper {

    public static AvailableTagDocument tagAvailableToDocument(TagAvailable tagAvailable) {

        return new AvailableTagDocument(tagAvailable.getName(), tagAvailable.getStatus());

    }

    public static TagAvailable documentToTagAvailable(AvailableTagDocument tagDocument) {
        return TagAvailable
                .builder()
                .name(tagDocument.getName())
                .build();
    }

    public static TagAvailable requestToTagAvailable(RequestTagDocument request) {
        return TagAvailable
                .builder()
                .name(request.getName())
                .build();
    }

}
