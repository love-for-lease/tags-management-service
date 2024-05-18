package com.leaseforlove.tagsmanagementservice.domain.models;

import com.leaseforlove.tagsmanagementservice.common.domain.Aggregate;
import com.leaseforlove.tagsmanagementservice.domain.events.TagRegisteredEvent;

public class Tag extends Aggregate {

    public void register(String name) {
        this.getEvents().add(new TagRegisteredEvent(name));
    }
}
