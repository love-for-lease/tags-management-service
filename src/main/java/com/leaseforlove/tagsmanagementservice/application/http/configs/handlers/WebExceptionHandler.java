package com.leaseforlove.tagsmanagementservice.application.http.configs.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class WebExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);
    private static final Map<String, HttpStatus> statusTable = new HashMap<>();

    static {
        statusTable.put("WebExceptionHandler", HttpStatus.BAD_REQUEST);
    }

}
