package com.matchmate.tagsmanagementservice.adapter.persistence;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.AvailableTagMongoRepository;
import com.matchmate.tagsmanagementservice.common.mappers.TagAvailableMapper;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;
import com.matchmate.tagsmanagementservice.domain.ports.TagAvailableStoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TagAvailableStoragePortImpl implements TagAvailableStoragePort {

    private final AvailableTagMongoRepository availableTagMongoRepository;

    @Override
    public TagAvailable save(TagAvailable tagAvailable) {
        AvailableTagDocument documentToBeSaved = TagAvailableMapper.tagAvailableToDocument(tagAvailable);

        AvailableTagDocument savedDocument = availableTagMongoRepository.save(documentToBeSaved);

        return TagAvailableMapper.documentToTagAvailable(savedDocument);
    }

    @Override
    public List<TagAvailable> saveAll(List<TagAvailable> tags) {
        List<AvailableTagDocument> listDocumentToBeSaved = tags
                .stream()
                .map(TagAvailableMapper::tagAvailableToDocument)
                .toList();

        List<AvailableTagDocument> savedDocumentList = availableTagMongoRepository.saveAll(listDocumentToBeSaved);

        return savedDocumentList
                .stream()
                .map(TagAvailableMapper::documentToTagAvailable)
                .toList();
    }
}
