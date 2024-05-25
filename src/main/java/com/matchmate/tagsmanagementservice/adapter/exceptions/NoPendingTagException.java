package com.matchmate.tagsmanagementservice.adapter.exceptions;

public class NoPendingTagException extends RuntimeException {
    public NoPendingTagException(String msg) {
        super(msg);
    }
}
