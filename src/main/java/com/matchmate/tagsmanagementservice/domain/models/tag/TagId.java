package com.matchmate.tagsmanagementservice.domain.models.tag;

import com.matchmate.tagsmanagementservice.common.domain.Identifier;

import java.util.UUID;

public class TagId extends Identifier<UUID> {
    protected TagId() {
        super(UUID.randomUUID());
    }
}
