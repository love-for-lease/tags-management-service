package com.matchmate.tagsmanagementservice.adapter.persistence;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.common.mappers.RequestTagMapper;
import com.matchmate.tagsmanagementservice.domain.models.tagrequest.RequestTag;
import com.matchmate.tagsmanagementservice.domain.ports.RequestTagPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RequestTagPersistencePortImpl implements RequestTagPersistencePort {
    private final RequestTagMongoRepository requestTagMongoRepository;

    @Override
    public void save(RequestTag requestTag) {
        Optional<RequestTagDocument> requestTagByName = requestTagMongoRepository.findByName(requestTag.getName());

        requestTagByName.ifPresent(requestTagDocument -> requestTagMongoRepository.save(
                RequestTagMapper.toDocument(requestTag, requestTagDocument.getRequestedAt()))
        );

        if(requestTagByName.isEmpty()) {
            requestTagMongoRepository.save(
                    RequestTagMapper.toDocumentWithoutRequestAt(requestTag));
        }
    }
}
