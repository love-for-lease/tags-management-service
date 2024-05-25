package com.matchmate.tagsmanagementservice.adapter.persistence;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.common.mappers.TagRequestMapper;
import com.matchmate.tagsmanagementservice.domain.models.TagRequest;
import com.matchmate.tagsmanagementservice.domain.ports.TagRequestStoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagRequestStoragePortImpl implements TagRequestStoragePort {

    private final RequestTagMongoRepository requestTagMongoRepository;

    @Override
    public TagRequest save(TagRequest tagRequest) {

        RequestTagDocument documentToBeSaved = TagRequestMapper.tagRequestToDocument(tagRequest);

        RequestTagDocument savedDocument = requestTagMongoRepository.save(documentToBeSaved);

        return TagRequestMapper.documentToTagRequest(savedDocument);
    }
}
