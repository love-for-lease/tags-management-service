package com.matchmate.tagsmanagementservice.adapter.persistence.services;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestTagService {

    private final RequestTagMongoRepository requestTagMongoRepository;

    public List<RequestTagDocument> findAllPendingTagsWaitingForAnalysis(OffsetDateTime dateRange) {
        return requestTagMongoRepository.findAllPendingTagsWaitingForAnalysis(dateRange);
    }

    public void delete(RequestTagDocument tagDocument) {
        requestTagMongoRepository.save(tagDocument);
    }

    public void deleteAll(List<RequestTagDocument> tagsDocuments) {
        requestTagMongoRepository.deleteAll(tagsDocuments);
    }
}
