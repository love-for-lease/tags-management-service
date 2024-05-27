package com.matchmate.tagsmanagementservice.domain.models.tagrequest;

import com.matchmate.tagsmanagementservice.common.domain.Identifier;

import java.util.UUID;

public class RequestTagId extends Identifier<UUID> {
    protected RequestTagId() {
        super(UUID.randomUUID());
    }

    protected RequestTagId(UUID id) {
        super(id);
    }
}
