package com.matchmate.tagsmanagementservice.common.event;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStoreMongoRepository extends MongoRepository<StoredEvent, String> {
}
