package com.matchmate.tagsmanagementservice.common.domain;

public interface UseCaseUnit<I> {
    void execute(I input);
}
