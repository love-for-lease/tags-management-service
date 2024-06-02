package com.matchmate.tagsmanagementservice.adapter.persistence.repository;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RequestTagMongoRepository extends MongoRepository<RequestTagDocument, UUID> {

    @Query("{ 'requests': { $gte: ?0 }, 'requested_at': { $gte: ?1 } }")
    List<RequestTagDocument> findByRequestedAtBetween(Integer minimumRequest, LocalDateTime dateTime);

    Optional<RequestTagDocument> findByName(String name);

    Optional<RequestTagDocument> findByNameIgnoreCase(String name);
}
