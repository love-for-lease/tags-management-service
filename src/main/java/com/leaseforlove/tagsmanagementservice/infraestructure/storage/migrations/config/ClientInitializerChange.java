package com.leaseforlove.tagsmanagementservice.infraestructure.storage.migrations.config;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;


@ChangeUnit(id="client-initializer", order = "001", author = "mongock")
public class ClientInitializerChange {

    private final MongoTemplate mongoTemplate;

    public ClientInitializerChange(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void changeSet() {
    }

    @RollbackExecution
    public void rollback() {
    }
}
