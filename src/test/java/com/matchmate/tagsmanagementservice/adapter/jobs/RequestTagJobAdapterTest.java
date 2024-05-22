package com.matchmate.tagsmanagementservice.adapter.jobs;

import com.matchmate.tagsmanagementservice.adapter.persistence.TagAvailableStoragePortImpl;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.common.mappers.TagAvailableMapper;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;
import com.matchmate.tagsmanagementservice.factories.tag.RequestTagDocumentFactory;
import com.matchmate.tagsmanagementservice.factories.tag.TagAvailableFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RequestTagJobAdapterTest {

    @Mock
    private RequestTagMongoRepository requestTagMongoRepository;

    @Mock
    private TagAvailableStoragePortImpl tagAvailableStoragePortImpl;

    @InjectMocks
    private RequestTagJobAdapter requestTagJobAdapter;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void analyzeRequestTags_ShouldProcessAndSaveTags() {


        List<RequestTagDocument> requestTagDocuments =
                RequestTagDocumentFactory.withSizeAndDateWith20Request(1, OffsetDateTime.now().minusDays(8));

        TagAvailable tagAvailable = TagAvailableFactory.withSize(1).get(0);

        List<TagAvailable> listOfTagAvailable = List.of(tagAvailable);


        when(requestTagMongoRepository.findAllPendingTagsWaitingForAnalysis(any(OffsetDateTime.class))).thenReturn(requestTagDocuments);
        mockStatic(TagAvailableMapper.class).when(() -> TagAvailableMapper.requestToTagAvailable(requestTagDocuments.get(0))).thenReturn(tagAvailable);
        when(tagAvailableStoragePortImpl.saveAll(anyList())).thenReturn(listOfTagAvailable);

        requestTagJobAdapter.analyzeRequestTags();

        verify(tagAvailableStoragePortImpl, times(1)).saveAll(listOfTagAvailable);
        verify(requestTagMongoRepository, times(1)).deleteAll(requestTagDocuments);
    }

}