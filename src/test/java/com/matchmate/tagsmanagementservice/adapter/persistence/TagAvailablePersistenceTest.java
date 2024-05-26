package com.matchmate.tagsmanagementservice.adapter.persistence;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.AvailableTagMongoRepository;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;
import com.matchmate.tagsmanagementservice.factories.tag.AvailableTagDocumentFactory;
import com.matchmate.tagsmanagementservice.factories.tag.TagAvailableFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TagAvailablePersistenceTest {
    @Mock
    private AvailableTagMongoRepository availableTagMongoRepository;

    @InjectMocks
    private TagAvailablePersistence tagAvailableStoragePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldTagAvailableStoragePortSave_AndVerifyResultsContainsSameName() {

        TagAvailable tagAvailable = TagAvailableFactory.withValidName("TAG_TEST");
        AvailableTagDocument savedDocument = AvailableTagDocumentFactory.validWithName("TAG_TEST");

        when(availableTagMongoRepository.save(any(AvailableTagDocument.class))).thenReturn(savedDocument);

        TagAvailable result = tagAvailableStoragePort.save(tagAvailable);

        assertEquals(tagAvailable.getName(), result.getName());
        assertEquals(tagAvailable.getStatus(), result.getStatus());
    }

    @Test
    void saveAll_ShouldTagAvailableStoragePortSaveList_AndVerifyListOfResultsContainsSameStatus() {
        List<AvailableTagDocument> tags = AvailableTagDocumentFactory.withSize(2);

        List<AvailableTagDocument> documentsToBeSaved = AvailableTagDocumentFactory.withSize(2);

        when(availableTagMongoRepository.saveAll(any(List.class))).thenReturn(documentsToBeSaved);

        List<TagAvailable> result = tagAvailableStoragePort.saveAll(tags);

        assertEquals(tags.size(), result.size());
        assertEquals(tags.get(0).getStatus(), result.get(0).getStatus());
        assertEquals(tags.get(1).getStatus(), result.get(1).getStatus());
    }
}