package com.matchmate.tagsmanagementservice.adapter.handlers;

import com.matchmate.tagsmanagementservice.adapter.FortuneFirstRequestTagVisitor;
import com.matchmate.tagsmanagementservice.adapter.exceptions.InvalidMessageException;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.repository.RequestTagMongoRepository;
import com.matchmate.tagsmanagementservice.adapter.queues.messages.RequestTagMessage;
import com.matchmate.tagsmanagementservice.domain.models.tagrequest.RequestTag;
import com.matchmate.tagsmanagementservice.domain.ports.ReceiveRequestTagPort;
import com.matchmate.tagsmanagementservice.factories.tag.RequestTagDocumentFactory;
import com.matchmate.tagsmanagementservice.factories.tag.RequestTagMessageFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterRequestTagConsumerHandlerTest {


    @InjectMocks
    private RegisterRequestTagConsumerHandler registerRequestTagConsumerHandler;

    @Mock
    private RequestTagMongoRepository requestTagMongoRepository;

    @Mock
    private ReceiveRequestTagPort receiveRequestTagPort;

    @Mock
    private FortuneFirstRequestTagVisitor fortuneFirstRequestTagVisitor;

    @Mock
    private UUID uuid;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handler_ShouldThrowInvalidMessageExceptionWhenNameIsBlank() {

        RequestTagMessage invalidMessage = RequestTagMessageFactory.withValidName(" ");

        Exception exception = assertThrows(IllegalStateException.class, () ->
                registerRequestTagConsumerHandler.handler(invalidMessage.name()));

        String expectedMessage = "request tag name must not be blank.";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void handler_ShouldIncreaseRequestCountWhenRequestExists() {

        RequestTagMessage validMessage = RequestTagMessageFactory.withValidName("TEST_MESSAGE");
        RequestTagDocument existingDocument = RequestTagDocumentFactory.validWithNameAndId("TEST_MESSAGE", UUID.randomUUID());
        RequestTag requestTag = new RequestTag(validMessage.name(), 1L);

        when(requestTagMongoRepository.findByNameIgnoreCase(validMessage.name())).thenReturn(Optional.of(existingDocument));
        when(receiveRequestTagPort.save(any(RequestTag.class))).thenReturn(requestTag);

        registerRequestTagConsumerHandler.handler(validMessage.name());

        verify(receiveRequestTagPort, times(1)).save(any(RequestTag.class));
        verify(requestTagMongoRepository, times(1)).findByNameIgnoreCase(validMessage.name());

        assertNotNull(requestTag.getId());
    }

    @Test
    void handler_ShouldCreateNewRequestWhenRequestDoesNotExist() {

        RequestTagMessage validMessage = RequestTagMessageFactory.withValidName("TEST_MESSAGE");
        var requestTag = new RequestTag(validMessage.name(), 1L);

        when(requestTagMongoRepository.findByNameIgnoreCase(validMessage.name())).thenReturn(Optional.empty());
        when(fortuneFirstRequestTagVisitor.visit(any(RequestTag.class))).thenReturn(requestTag);
        when(receiveRequestTagPort.save(any(RequestTag.class))).thenReturn(requestTag);

        registerRequestTagConsumerHandler.handler(validMessage.name());

        verify(receiveRequestTagPort, times(1)).save(any(RequestTag.class));
        verify(requestTagMongoRepository, times(1)).findByNameIgnoreCase(validMessage.name());

        assertNotNull(requestTag.getId());
    }
}