package com.matchmate.tagsmanagementservice.application.services;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.application.web.dto.RequestTagDto;
import com.matchmate.tagsmanagementservice.common.mappers.RequestTagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RequestTagService {

    private final RequestTagMongoRepository requestTagMongoRepository;

    public Page<RequestTagDto> findAll(Pageable pageable) {
        Page<RequestTagDocument> pageOfTagDocument = requestTagMongoRepository.findAll(pageable);

        return pageOfTagDocument.map(RequestTagMapper::toDto);
    }

}
