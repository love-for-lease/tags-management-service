package com.matchmate.tagsmanagementservice.adapter.persistence.repository;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvailableTagMongoRepository extends MongoRepository<AvailableTagDocument, UUID> {
    Optional<AvailableTagDocument> findByName(String name);
}
