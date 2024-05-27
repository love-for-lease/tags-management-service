package com.matchmate.tagsmanagementservice.common.visitor;

public interface Visitable<T> {
    void accept(final Visitor<T, ?> visitor);
}
