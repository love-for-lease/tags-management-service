package com.matchmate.tagsmanagementservice.application.http.handlers;

import com.matchmate.tagsmanagementservice.application.web.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class WebExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> onDefaultHandler(Exception ex, HttpServletRequest request) {
        log.error("Exception handled", ex);
        var details = new HashMap<String, String>();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(
                        HttpStatus.BAD_REQUEST.name(),
                        ex.getCause().getMessage(),
                        details.put("cause", ex.getMessage())
                ));
    }

    private ResponseEntity<ErrorResponseDto> responseInvalidArgs(Map<String, Object> details, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponseDto("ARGUMENT_NOT_VALID",
                        "Invalid arguments, see more in [details]",
                        details
                )
        );
    }

    private ResponseEntity<ErrorResponseDto> responseUriNotFound(Map<String, Object> details, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponseDto("ENDPOINT_NOT_FOUND",
                        "The endpoint given was not found, see more in [details]",
                        details
                )
        );
    }
}
