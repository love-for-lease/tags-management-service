package com.matchmate.tagsmanagementservice.common.domain;

import com.matchmate.tagsmanagementservice.common.observer.Observable;
import lombok.Getter;

@Getter
public abstract class Aggregate<ID extends Identifier> extends Observable {

    private final ID id;

    protected Aggregate(ID id) {
        super();
        this.id = id;
    }
}
