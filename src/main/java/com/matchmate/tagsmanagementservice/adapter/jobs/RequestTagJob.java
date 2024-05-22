package com.matchmate.tagsmanagementservice.adapter.jobs;

import com.matchmate.tagsmanagementservice.adapter.persistence.TagAvailableStoragePortImpl;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.services.RequestTagService;
import com.matchmate.tagsmanagementservice.common.mappers.TagAvailableMapper;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;
import com.matchmate.tagsmanagementservice.domain.ports.RequestTagPort;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.OffsetDateTime;
import java.util.List;

@RequiredArgsConstructor
public class RequestTagJob implements RequestTagPort {

    private final RequestTagService requestTagService;
    private final TagAvailableStoragePortImpl tagAvailableStoragePortImpl;


    @Scheduled(cron = "${app.cron}")
    public void analyzeRequestTags() {

        OffsetDateTime now = OffsetDateTime.now();
        List<RequestTagDocument> pendingTags = requestTagService.findAllPendingTagsWaitingForAnalysis(now.minusDays(7));

        List<TagAvailable> listTagsToBeSaved = pendingTags
                        .stream()
                        .map(TagAvailableMapper::requestToTagAvailable)
                        .toList();

        tagAvailableStoragePortImpl.saveAll(listTagsToBeSaved);

        requestTagService.deleteAll(pendingTags);
    }

}
