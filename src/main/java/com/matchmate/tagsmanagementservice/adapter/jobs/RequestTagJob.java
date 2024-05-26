package com.matchmate.tagsmanagementservice.adapter.jobs;

import com.matchmate.tagsmanagementservice.adapter.exceptions.NoPendingTagException;
import com.matchmate.tagsmanagementservice.adapter.persistence.TagAvailablePersistence;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.application.properties.RequestTagAnalyzeProperties;
import com.matchmate.tagsmanagementservice.common.mappers.TagAvailableMapper;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class RequestTagJob {

    private final TagAvailablePersistence tagAvailablePersistence;
    private final RequestTagMongoRepository requestTagMongoRepository;
    private final RequestTagAnalyzeProperties requestTagAnalyzeProperties;


    @Scheduled(cron = "${app.analyse-periodic-request-tags.cron}")
    public void analyzeRequestTags() {

        log.info("Analyzing request tags: start time {}", OffsetDateTime.now());

        String dateRange = requestTagAnalyzeProperties.getRangeDateAnalyze();

        OffsetDateTime now = OffsetDateTime.now();
        List<RequestTagDocument> pendingTags = requestTagMongoRepository
                .findByDateBeforeAndRequestsGreaterThanEqual(
                        now.minusDays(Long.parseLong(dateRange)),
                        Integer.valueOf(requestTagAnalyzeProperties.getMinimumRequest())
                );

        if(pendingTags.isEmpty()) {
            throw new NoPendingTagException("No pending requests exist.");
        }

        List<TagAvailable> listTagsToBeSaved = pendingTags
                        .stream()
                        .map(TagAvailableMapper::requestToTagAvailable)
                        .toList();

        List<AvailableTagDocument> listDocumentToBeSaved = listTagsToBeSaved
                .stream()
                .map(TagAvailableMapper::tagAvailableToDocument)
                .toList();

        log.info("Saving available tags: {}", listDocumentToBeSaved.size());
        tagAvailablePersistence.saveAll(listDocumentToBeSaved);

        log.info("Deleting pending request tags: {}", pendingTags.size());
        requestTagMongoRepository.deleteAll(pendingTags);

        log.info("Finished analyzing request tags: end time {}", OffsetDateTime.now());
    }

}
