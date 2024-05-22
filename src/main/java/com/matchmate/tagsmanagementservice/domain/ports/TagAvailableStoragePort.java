package com.matchmate.tagsmanagementservice.domain.ports;

import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;

import java.util.List;

public interface TagAvailableStoragePort {
    TagAvailable save(TagAvailable tag);
    List<TagAvailable> saveAll(List<TagAvailable> tags);
}
