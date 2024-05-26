package com.matchmate.tagsmanagementservice.domain.ports;

import com.matchmate.tagsmanagementservice.domain.models.tag.Tag;

import java.util.List;

public interface TagPersistencePort {
    void save(List<Tag> tags);
    Tag findByName(String name);
}
