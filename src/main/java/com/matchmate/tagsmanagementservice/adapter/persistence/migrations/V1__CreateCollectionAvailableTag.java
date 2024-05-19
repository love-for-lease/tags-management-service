package com.matchmate.tagsmanagementservice.adapter.persistence.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;


@ChangeUnit(id="12.05.2024-create-collection-available-tag", order = "001", author = "FredyHG")
@RequiredArgsConstructor
public class V1__CreateCollectionAvailableTag {

    private final MongoTemplate mongoTemplate;

    @Execution
    public void changeSet() {
        mongoTemplate.createCollection("available-tag");
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.dropCollection("available-tag");
    }
}
