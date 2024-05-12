package com.leaseforlove.tagsmanagementservice.infraestructure.storage.migrations.config;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;


@ChangeUnit(id="12.05.2024-create-collection-tags-management", order = "001", author = "FredyHG")
public class V1__CreateCollectionTagsManagement {

    private final MongoTemplate mongoTemplate;

    public V1__CreateCollectionTagsManagement(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void changeSet() {
        mongoTemplate.createCollection("tags-management");
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.dropCollection("tags-management");
    }
}
