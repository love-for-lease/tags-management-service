package com.matchmate.tagsmanagementservice.adapter.persistence;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.AvailableTagMongoRepository;
import com.matchmate.tagsmanagementservice.common.mappers.TagAvailableMapper;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TagAvailablePersistence {

    private final AvailableTagMongoRepository availableTagMongoRepository;

    public TagAvailable save(TagAvailable tagAvailable) {
        log.info("Saving tag available: {}", tagAvailable.getName());

        AvailableTagDocument documentToBeSaved = TagAvailableMapper.tagAvailableToDocument(tagAvailable);

        AvailableTagDocument savedDocument = availableTagMongoRepository.save(documentToBeSaved);

        return TagAvailableMapper.documentToTagAvailable(savedDocument);
    }

    public List<TagAvailable> saveAll(List<AvailableTagDocument> tags) {
        log.info("Saving tags available: {}", tags.size());

        List<AvailableTagDocument> savedDocumentList = availableTagMongoRepository.saveAll(tags);

        return savedDocumentList
                .stream()
                .map(TagAvailableMapper::documentToTagAvailable)
                .toList();
    }
}
