package com.matchmate.tagsmanagementservice.adapter.jobs;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.AvailableTagMongoRepository;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.application.properties.RequestTagAnalyzeProperties;
import com.matchmate.tagsmanagementservice.domain.ports.RegisterTagPort;
import com.matchmate.tagsmanagementservice.factories.tag.AvailableTagDocumentFactory;
import com.matchmate.tagsmanagementservice.factories.tag.RequestTagDocumentFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RequestTagJobTest {

    @Mock
    private RequestTagMongoRepository requestTagMongoRepository;

    @Mock
    private AvailableTagMongoRepository availableTagMongoRepository;

    @Mock
    private RegisterTagPort registerTagPort;

    @Mock
    private RequestTagAnalyzeProperties requestTagAnalyzeProperties;


    @InjectMocks
    private RequestTagJob requestTagJob;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void analyzeRequestTags_ShouldProcessAndSaveTags() {


        List<RequestTagDocument> listOfRequestTagDocument = RequestTagDocumentFactory.withSizeWith20Request(2);
        List<AvailableTagDocument> listOfAvailableTagDocument = AvailableTagDocumentFactory.withSize(2);


        when(requestTagMongoRepository.findByDateBeforeAndRequestsGreaterThanEqual(any(), any())).thenReturn(listOfRequestTagDocument);
        when(availableTagMongoRepository.saveAll(listOfAvailableTagDocument)).thenReturn(listOfAvailableTagDocument);
        when(requestTagAnalyzeProperties.getMinimumRequest()).thenReturn("20");
        when(requestTagAnalyzeProperties.getRangeDateAnalyze()).thenReturn("7");

        requestTagJob.analyzeRequestTags();

        verify(registerTagPort, times(1)).register(anyList());
        verify(requestTagMongoRepository, times(1)).deleteAll(anyList());
        assertDoesNotThrow(() -> requestTagJob.analyzeRequestTags());
    }

    @Test
    void analyzeRequestTags_ShouldThrowExceptionWhenNotExistingPendingRequestTags() {

        String expectedErrorMessage = "No pending requests exist.";

        when(requestTagAnalyzeProperties.getMinimumRequest()).thenReturn("20");
        when(requestTagAnalyzeProperties.getRangeDateAnalyze()).thenReturn("7");

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> requestTagJob.analyzeRequestTags());

        assertEquals(expectedErrorMessage, runtimeException.getMessage());
    }
}