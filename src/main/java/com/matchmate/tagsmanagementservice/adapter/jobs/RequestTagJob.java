package com.matchmate.tagsmanagementservice.adapter.jobs;

import com.matchmate.tagsmanagementservice.adapter.persistence.TagAvailableStoragePortImpl;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.common.mappers.TagAvailableMapper;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;
import com.matchmate.tagsmanagementservice.domain.ports.RequestTagPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class RequestTagJob implements RequestTagPort {

    private final TagAvailableStoragePortImpl tagAvailableStoragePortImpl;
    private final RequestTagMongoRepository requestTagMongoRepository;

    @Value("${app.analyse-periodic-request-tags.minimum-request}")
    private String  minimumRequest;


    @Scheduled(cron = "${app.analyse-periodic-request-tags.cron}")
    public void analyzeRequestTags() {

        log.info("Analyzing request tags");

        OffsetDateTime now = OffsetDateTime.now();
        List<RequestTagDocument> pendingTags = requestTagMongoRepository
                .findByDateBeforeAndRequestsGreaterThanEqual(now.minusDays(7),Integer.valueOf(minimumRequest));

        List<TagAvailable> listTagsToBeSaved = pendingTags
                        .stream()
                        .map(TagAvailableMapper::requestToTagAvailable)
                        .toList();

        List<AvailableTagDocument> listDocumentToBeSaved = listTagsToBeSaved
                .stream()
                .map(TagAvailableMapper::tagAvailableToDocument)
                .toList();

        log.info("Saving available tags");
        tagAvailableStoragePortImpl.saveAll(listDocumentToBeSaved);

        log.info("Deleting pending request tags");
        requestTagMongoRepository.deleteAll(pendingTags);

        log.info("Finished analyzing request tags");
    }

}
