package com.matchmate.tagsmanagementservice.adapter.handlers;

import com.matchmate.tagsmanagementservice.adapter.FortuneFirstRequestTagVisitor;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.common.mappers.RequestTagMapper;
import com.matchmate.tagsmanagementservice.domain.models.tagrequest.RequestTag;
import com.matchmate.tagsmanagementservice.domain.ports.ReceiveRequestTagPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterRequestTagConsumerHandler {

    private final RequestTagMongoRepository requestTagMongoRepository;
    private final ReceiveRequestTagPort receiveRequestTagPort;
    private final FortuneFirstRequestTagVisitor fortuneFirstRequestTagVisitor;

    public void handler(String requestName) {
        log.info("Checking if request already exist with name: {}", requestName);
        Optional<RequestTagDocument> requestTagDocument = requestTagMongoRepository.findByNameIgnoreCase(requestName);

        requestTagDocument.ifPresentOrElse(tag -> {
            log.info("Request already exists, increasing request");
            receiveRequestTagPort.save(RequestTagMapper.toDomain(tag));
        }, () -> {
            log.info("Creating first request tag: {}", requestName);
            RequestTag requestTag = new RequestTag(requestName);
            requestTag.accept(fortuneFirstRequestTagVisitor);
            receiveRequestTagPort.save(requestTag);
            log.info("Create request: {}", requestName);
        });
    }
}
