package com.matchmate.tagsmanagementservice.adapter.exceptions;

public class RequestTagNotFoundException extends RuntimeException {
    public RequestTagNotFoundException(String msg) {
        super(msg);
    }
}
