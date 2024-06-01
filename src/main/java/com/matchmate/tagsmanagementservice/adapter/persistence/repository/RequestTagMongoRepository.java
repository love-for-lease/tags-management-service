package com.matchmate.tagsmanagementservice.adapter.persistence.repository;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RequestTagMongoRepository extends MongoRepository<RequestTagDocument, UUID> {

    @Query("{ 'requested_at': { $lt: ?0 }, 'requests': { $gt: ?1 } }")
    List<RequestTagDocument> findByDateBeforeAndRequestsGreaterThanEqual(ZonedDateTime dateTime, Integer minimumRequest);

    Optional<RequestTagDocument> findByName(String name);

    Optional<RequestTagDocument> findByNameIgnoreCase(String name);
}
