package com.matchmate.tagsmanagementservice.common.domain;

public abstract class Identifier<T> {
    private final T value;

    protected Identifier(T value) {
        this.value = value;
    }

    public String fromValue() {
        return this.value.toString();
    }
}
