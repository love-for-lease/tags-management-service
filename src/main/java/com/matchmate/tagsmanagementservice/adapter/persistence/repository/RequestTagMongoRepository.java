package com.matchmate.tagsmanagementservice.adapter.persistence.repository;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RequestTagMongoRepository extends MongoRepository<RequestTagDocument, UUID> {

    @Query("{ 'date': { $lt: ?0 }, 'requests': { $gte: ?1 } }")
    List<RequestTagDocument> findByDateBeforeAndRequestsGreaterThanEqual(OffsetDateTime dateRange, Integer minimumRequest);

    Optional<RequestTagDocument> findByName(String name);
}
