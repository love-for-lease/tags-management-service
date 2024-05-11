package com.leaseforlove.tagsmanagementservice.application.http.configs.handlers;

import com.leaseforlove.tagsmanagementservice.application.web.dto.ErrorMessageDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class WebExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageDTO> onDefaultHandler(Exception ex, HttpServletRequest request) {
        log.error("Exception handled", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessageDTO
                        .builder()
                        .type(HttpStatus.BAD_REQUEST.name())
                        .details(Collections.singletonMap("cause", ex.getCause()))
                        .uri(request.getRequestURI())
                        .build()
                );
    }

    private ResponseEntity<ErrorMessageDTO> responseInvalidArgs(Map<String, Object> details, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorMessageDTO
                        .builder()
                        .type("ARGUMENT_NOT_VALID")
                        .message("Invalid arguments, see more in [details]")
                        .uri(request.getRequestURI())
                        .details(details)
                        .build()
                );
    }

    private ResponseEntity<ErrorMessageDTO> responseUriNotFound(Map<String, Object> details, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorMessageDTO
                        .builder()
                        .type("ENDPOINT_NOT_FOUND")
                        .message("The endpoint given was not found, see more in [details]")
                        .uri(request.getRequestURI())
                        .details(details)
                        .build()
                );
    }
}
