package com.leaseforlove.tagsmanagementservice.common.event;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventStoreMongoRepository extends MongoRepository<StoredEvent, UUID> {
}
