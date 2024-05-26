package com.matchmate.tagsmanagementservice.adapter.handlers;

import com.matchmate.tagsmanagementservice.adapter.exceptions.InvalidMessageException;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.adapter.queues.messages.RequestTagMessage;
import com.matchmate.tagsmanagementservice.factories.tag.RequestTagDocumentFactory;
import com.matchmate.tagsmanagementservice.factories.tag.RequestTagMessageFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterRequestTagConsumerHandlerTest {


    @InjectMocks
    private RegisterRequestTagConsumerHandler registerRequestTagConsumerHandler;

    @Mock
    private RequestTagMongoRepository requestTagMongoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handler_ShouldThrowInvalidMessageExceptionWhenNameIsBlank() {

        RequestTagMessage invalidMessage = RequestTagMessageFactory.withValidName(" ");

        Exception exception = assertThrows(InvalidMessageException.class, () -> {
            registerRequestTagConsumerHandler.handler(invalidMessage);
        });

        String expectedMessage = "Request name wasn't informed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void handler_ShouldIncreaseRequestCountWhenRequestExists() {

        RequestTagMessage validMessage = RequestTagMessageFactory.withValidName("TEST_MESSAGE");
        RequestTagDocument existingDocument = RequestTagDocumentFactory.validWithName("TEST_MESSAGE");

        when(requestTagMongoRepository.findByName(validMessage.getName())).thenReturn(Optional.of(existingDocument));

        registerRequestTagConsumerHandler.handler(validMessage);

        verify(requestTagMongoRepository, times(1)).save(any(RequestTagDocument.class));
        verify(requestTagMongoRepository, times(1)).findByName(validMessage.getName());
    }

    @Test
    void handler_ShouldCreateNewRequestWhenRequestDoesNotExist() {

        RequestTagMessage validMessage = RequestTagMessageFactory.withValidName("TEST_MESSAGE");

        when(requestTagMongoRepository.findByName(validMessage.getName())).thenReturn(Optional.empty());

        registerRequestTagConsumerHandler.handler(validMessage);

        verify(requestTagMongoRepository, times(1)).save(any(RequestTagDocument.class));
        verify(requestTagMongoRepository, times(1)).findByName(validMessage.getName());
    }

}