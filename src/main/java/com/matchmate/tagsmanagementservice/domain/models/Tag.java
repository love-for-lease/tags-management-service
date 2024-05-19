package com.matchmate.tagsmanagementservice.domain.models;

import com.matchmate.tagsmanagementservice.common.domain.Aggregate;
import com.matchmate.tagsmanagementservice.domain.events.TagRegisteredEvent;

public class Tag extends Aggregate {

    public void register(String name) {
        this.getEvents().add(new TagRegisteredEvent(name));
    }
}
