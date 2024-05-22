package com.matchmate.tagsmanagementservice.adapter.persistence;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.domain.models.TagRequest;
import com.matchmate.tagsmanagementservice.factories.tag.RequestTagDocumentFactory;
import com.matchmate.tagsmanagementservice.factories.tag.TagRequestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TagRequestStoragePortImplTest {
    @Mock
    private RequestTagMongoRepository requestTagMongoRepository;

    @InjectMocks
    private TagRequestStoragePortImpl tagRequestStoragePortImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldStoreTagRegisteredEvent_AndVerifyEventBodyContainsTagName() {

        TagRequest tagRequest = TagRequestFactory.withValidName("TAG_TEST");
        RequestTagDocument savedDocument = RequestTagDocumentFactory.validWithName("TAG_TEST");

        when(requestTagMongoRepository.save(any(RequestTagDocument.class))).thenReturn(savedDocument);

        TagRequest result = tagRequestStoragePortImpl.save(tagRequest);

        assertEquals(tagRequest.getName(), result.getName());
    }
}