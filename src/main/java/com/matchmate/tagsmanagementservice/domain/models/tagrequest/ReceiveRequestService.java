package com.matchmate.tagsmanagementservice.domain.models.tagrequest;

import com.matchmate.tagsmanagementservice.domain.ports.ReceiveRequestTagPort;
import com.matchmate.tagsmanagementservice.domain.ports.RequestTagPersistencePort;

public class ReceiveRequestService implements ReceiveRequestTagPort {
    private final RequestTagPersistencePort requestTagPersistencePort;

    public ReceiveRequestService(RequestTagPersistencePort requestTagPersistencePort) {
        this.requestTagPersistencePort = requestTagPersistencePort;
    }

    @Override
    public RequestTag save(RequestTag requestTag) {
        requestTag.increment();
        requestTagPersistencePort.save(requestTag);
        return requestTag;
    }
}
