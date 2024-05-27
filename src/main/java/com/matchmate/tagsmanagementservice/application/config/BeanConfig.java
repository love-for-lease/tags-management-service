package com.matchmate.tagsmanagementservice.application.config;

import com.matchmate.tagsmanagementservice.adapter.persistence.RequestTagPersistencePortImpl;
import com.matchmate.tagsmanagementservice.adapter.persistence.AvailableTagPersistencePortImpl;
import com.matchmate.tagsmanagementservice.domain.models.tag.RegisterTagService;
import com.matchmate.tagsmanagementservice.domain.models.tagrequest.ReceiveRequestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public RegisterTagService registerTagService(
            AvailableTagPersistencePortImpl availableTagPersistencePortImpl
    ) {
        return new RegisterTagService(availableTagPersistencePortImpl);
    }

    @Bean
    public ReceiveRequestService receiveRequestService(
            RequestTagPersistencePortImpl requestTagPersistencePortImpl
    ) {
        return new ReceiveRequestService(requestTagPersistencePortImpl);
    }
}
