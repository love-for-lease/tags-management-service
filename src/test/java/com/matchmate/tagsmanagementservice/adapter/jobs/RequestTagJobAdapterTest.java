package com.matchmate.tagsmanagementservice.adapter.jobs;

import com.matchmate.tagsmanagementservice.adapter.persistence.TagAvailableStoragePortImpl;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.AvailableTagMongoRepository;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RequestTagJobAdapterTest {

    @Mock
    private RequestTagMongoRepository requestTagMongoRepository;

    @Mock
    private AvailableTagMongoRepository availableTagMongoRepository;

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
        //WAITING CONFIG MONGO FOR CREATE TEST
    }

}