package com.matchmate.tagsmanagementservice.domain.ports;

import com.matchmate.tagsmanagementservice.domain.models.tagrequest.RequestTag;

public interface RequestTagPersistencePort {
    void save(RequestTag requestTag);
}
