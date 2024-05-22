package com.matchmate.tagsmanagementservice.adapter.jobs;

import com.matchmate.tagsmanagementservice.adapter.persistence.TagAvailableStoragePortImpl;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.common.mappers.TagAvailableMapper;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;
import com.matchmate.tagsmanagementservice.domain.ports.RequestTagPort;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.OffsetDateTime;
import java.util.List;

@RequiredArgsConstructor
public class RequestTagJobAdapter implements RequestTagPort {

    private final TagAvailableStoragePortImpl tagAvailableStoragePortImpl;
    private final RequestTagMongoRepository requestTagMongoRepository;


    @Scheduled(cron = "${app.cron.analyse-periodic-tags.time}")
    public void analyzeRequestTags() {

        OffsetDateTime now = OffsetDateTime.now();
        List<RequestTagDocument> pendingTags = requestTagMongoRepository.findAllPendingTagsWaitingForAnalysis(now.minusDays(7));

        List<TagAvailable> listTagsToBeSaved = pendingTags
                        .stream()
                        .map(TagAvailableMapper::requestToTagAvailable)
                        .toList();

        tagAvailableStoragePortImpl.saveAll(listTagsToBeSaved);

        requestTagMongoRepository.deleteAll(pendingTags);
    }

}
