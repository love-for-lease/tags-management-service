package com.matchmate.tagsmanagementservice.application.services;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.AvailableTagMongoRepository;
import com.matchmate.tagsmanagementservice.application.web.dto.AvailableTagDto;
import com.matchmate.tagsmanagementservice.common.mappers.AvailableTagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AvailableTagService {

    private final AvailableTagMongoRepository availableTagMongoRepository;

    public Page<AvailableTagDto> findAll(Pageable pageable) {
        Page<AvailableTagDocument> pageOfTagDocument = availableTagMongoRepository.findAll(pageable);

        return pageOfTagDocument.map(AvailableTagMapper::toDto);
    }

}
