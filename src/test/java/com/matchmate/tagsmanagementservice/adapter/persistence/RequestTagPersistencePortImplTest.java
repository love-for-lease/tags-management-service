package com.matchmate.tagsmanagementservice.adapter.persistence;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.domain.models.tagrequest.RequestTag;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

class RequestTagPersistencePortImplTest {

    @Mock
    private RequestTagMongoRepository requestTagMongoRepository;

    @InjectMocks
    private RequestTagPersistencePortImpl requestTagPersistencePortImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave_WhenRequestTagExists() {
        RequestTag requestTag = new RequestTag("sushi", 2L);

        RequestTagDocument existingDocument = new RequestTagDocument(UUID.randomUUID().toString(),
                "sushi",
                1L,
                OffsetDateTime.now());

        when(requestTagMongoRepository.findByName("sushi")).thenReturn(Optional.of(existingDocument));

        requestTagPersistencePortImpl.save(requestTag);

        ArgumentCaptor<RequestTagDocument> requestTagDocumentCaptor = ArgumentCaptor.forClass(RequestTagDocument.class);
        verify(requestTagMongoRepository, times(1)).save(requestTagDocumentCaptor.capture());
        var requestTagDocumentCapturedValue = requestTagDocumentCaptor.getValue();

        assertNotNull(requestTagDocumentCapturedValue.getId());
    }

    @Test
    void testSave_WhenRequestTagDoesNotExist() {
        RequestTag requestTag = new RequestTag("sushi", 1L);

        when(requestTagMongoRepository.findByName("sushi")).thenReturn(Optional.empty());

        requestTagPersistencePortImpl.save(requestTag);

        ArgumentCaptor<RequestTagDocument> requestTagDocumentCaptor = ArgumentCaptor.forClass(RequestTagDocument.class);
        verify(requestTagMongoRepository, times(1)).save(requestTagDocumentCaptor.capture());
        var requestTagDocumentCapturedValue = requestTagDocumentCaptor.getValue();

        assertNotNull(requestTagDocumentCapturedValue.getId());
        assertNotNull(requestTagDocumentCapturedValue.getRequestedAt());
    }
}