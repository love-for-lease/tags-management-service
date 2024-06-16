package com.matchmate.tagsmanagementservice.adapter.persistence;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.AvailableTagMongoRepository;
import com.matchmate.tagsmanagementservice.common.mappers.AvailableTagMapper;
import com.matchmate.tagsmanagementservice.domain.models.tag.Tag;
import com.matchmate.tagsmanagementservice.domain.ports.TagPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AvailableTagPersistencePortImpl implements TagPersistencePort {
    private final AvailableTagMongoRepository availableTagMongoRepository;

    @Override
    public void save(List<Tag> tags) {
        log.info("Saving available tags: {}", tags.size());
        availableTagMongoRepository.saveAll(AvailableTagMapper.fromDomain(tags));
    }

    @Override
    public Tag findByName(String name) {
        AvailableTagDocument availableTagDocument = availableTagMongoRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("not found"));
        return AvailableTagMapper.fromDocument(availableTagDocument);
    }
}
