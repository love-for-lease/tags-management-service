package com.matchmate.tagsmanagementservice.adapter.jobs;

import com.matchmate.tagsmanagementservice.adapter.exceptions.NoPendingTagException;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.application.properties.RequestTagAnalyzeProperties;
import com.matchmate.tagsmanagementservice.domain.ports.RegisterTagPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class RequestTagJob {

    private final RequestTagMongoRepository requestTagMongoRepository;
    private final RequestTagAnalyzeProperties requestTagAnalyzeProperties;
    private final RegisterTagPort registerTagPort;

    @Scheduled(cron = "${app.analyse-periodic-request-tags.cron}")
    public void analyzeRequestTags() {
        OffsetDateTime currentDate = OffsetDateTime.now();

        log.info("Analyzing request tags: start time {}", currentDate);

        String dateRange = requestTagAnalyzeProperties.getRangeDateAnalyze();

        List<RequestTagDocument> requestTags = requestTagMongoRepository
                .findByDateBeforeAndRequestsGreaterThanEqual(
                        currentDate.minusDays(Long.parseLong(dateRange)),
                        Integer.valueOf(requestTagAnalyzeProperties.getMinimumRequest()));

        if(requestTags.isEmpty()) {
            throw new NoPendingTagException("No pending requests exist.");
        }

        registerTagPort.register(requestTags.stream().map(RequestTagDocument::getName).toList());

        log.info("Deleting pending request tags: {}", requestTags.size());
        requestTagMongoRepository.deleteAll(requestTags);

        log.info("Finished analyzing request tags: end time {}", OffsetDateTime.now());
    }
}
