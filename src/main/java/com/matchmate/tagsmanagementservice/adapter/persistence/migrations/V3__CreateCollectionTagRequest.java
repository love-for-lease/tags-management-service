package com.matchmate.tagsmanagementservice.adapter.persistence.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "19.05.2024-create-collection-request-tag", order = "001", author = "gabrieldasilvadev")
@RequiredArgsConstructor
public class V3__CreateCollectionTagRequest {
    private final MongoTemplate mongoTemplate;

    @Execution
    public void changeSet() {
        mongoTemplate.createCollection("request-tag");
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.dropCollection("request-tag");
    }
}
