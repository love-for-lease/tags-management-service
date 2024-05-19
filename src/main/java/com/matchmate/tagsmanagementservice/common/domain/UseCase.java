package com.matchmate.tagsmanagementservice.common.domain;

public interface UseCase<I, O> {
    O execute(I input);
}
