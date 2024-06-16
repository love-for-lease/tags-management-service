package com.matchmate.tagsmanagementservice.adapter.jobs;

import com.matchmate.tagsmanagementservice.adapter.handlers.TagsRegisteredEventHandler;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.application.properties.RequestTagAnalyzeProperties;
import com.matchmate.tagsmanagementservice.domain.ports.RegisterTagPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class TagRequestJob {

    private final RequestTagMongoRepository requestTagMongoRepository;
    private final RequestTagAnalyzeProperties requestTagAnalyzeProperties;
    private final RegisterTagPort registerTagPort;
    private final TagsRegisteredEventHandler queue;

    @Scheduled(cron = "${app.analyse-periodic-request-tags.cron}")
    public void analyzeRequestTags() {
        ZonedDateTime currentDate = ZonedDateTime.now();

        log.info("Analyzing request tags: start time {}", currentDate);

        List<RequestTagDocument> requestTags = requestTagMongoRepository
                .findByRequestedAtBetween(requestTagAnalyzeProperties.getMinimumRequest(),
                        currentDate.minusDays(requestTagAnalyzeProperties.getRangeDateAnalyze()).toLocalDateTime());

        if (requestTags.isEmpty()) {
            log.info("No pending requests exist.");
            return;
        }
        registerTagPort.register(requestTags.stream().map(RequestTagDocument::getName).toList());

        log.info("Deleting pending request tags: {}", requestTags.size());
        requestTagMongoRepository.deleteAll(requestTags);
    }
}
