package com.matchmate.tagsmanagementservice.domain.ports;

import com.matchmate.tagsmanagementservice.domain.models.TagRequest;

public interface TagRequestStoragePort {
    TagRequest save(TagRequest tagRequest);
}
