package com.matchmate.tagsmanagementservice.adapter.persistence.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;

@ChangeUnit(id = "19.05.2024-create-indexes-request-tag", order = "001", author = "gabrieldasilvadev")
@RequiredArgsConstructor
public class V4__CreateIndexesTagRequest {
    private final MongoTemplate mongoTemplate;

    @Execution
    public void changeSet() {
        IndexOperations indexOps = mongoTemplate.indexOps("request-tag");
        indexOps.ensureIndex(new Index("name", Sort.Direction.DESC)
                .unique()
                .background());
        indexOps.ensureIndex(new Index("requested_at", Sort.Direction.DESC).background());
    }

    @RollbackExecution
    public void rollback() {
    }
}
