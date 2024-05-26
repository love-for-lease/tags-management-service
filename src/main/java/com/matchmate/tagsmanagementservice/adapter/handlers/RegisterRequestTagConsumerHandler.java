package com.matchmate.tagsmanagementservice.adapter.handlers;

import com.matchmate.tagsmanagementservice.adapter.exceptions.InvalidMessageException;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.adapter.queues.messages.RequestTagMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterRequestTagConsumerHandler {

    private final RequestTagMongoRepository requestTagMongoRepository;

    public void handler(RequestTagMessage requestTagMessage) {

        if(requestTagMessage.getName().isEmpty()
                || StringUtils.isBlank(requestTagMessage.getName())
        ){
            throw new InvalidMessageException("Request name wasn't informed");
        }

        RequestTagDocument requestToBeSaved = new RequestTagDocument(requestTagMessage.getName());

        log.info("Checking if request already exist with name: {}", requestTagMessage.getName());
        Optional<RequestTagDocument> requestExists = requestTagMongoRepository.findByName(requestToBeSaved.getName());

        if(requestExists.isPresent()) {
            log.info("Request already exists, increasing request");

            RequestTagDocument requestTagDocument = requestExists.get().incrementRequest();
            requestTagMongoRepository.save(requestTagDocument);

            return;
        }

        log.info("Create request: {}", requestToBeSaved.getName());
        requestTagMongoRepository.save(requestToBeSaved);
    }
}
